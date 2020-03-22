package com.alnt.platform.base.persistence;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.Assert;

import com.alnt.platform.base.annotations.AlwaysReplaceForMerge;
import com.alnt.platform.base.annotations.IgnoreEntitiesForMerge;
import com.alnt.platform.base.annotations.ParentEntityReference;
import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.BaseEntity.INT_STATUS;
import com.google.common.collect.Lists;

import jodd.bean.BeanUtil;
import play.Logger;

public class EntityPersistFixer {
	
	@SuppressWarnings("unused")
	private static Logger.ALogger LOG = Logger.of(EntityPersistFixer.class );
	
	private List<Object> lookingAt;
	private List<Object> lookingAtForMerge;
	Map<String, Object> entityCache;
	private Map<String, Boolean> entitiesToIgnoreForMerge;
	private boolean doNotReplaceList = true;
	private EntityManager em;
	
	
	public EntityPersistFixer(EntityManager em) {
		super();
		this.em = em;
		this.lookingAt = new ArrayList<>();
		this.lookingAtForMerge = new ArrayList<>();
		this.entityCache = new HashMap<>();
		this.entitiesToIgnoreForMerge = new HashMap<>();
	}


	@Transactional(value=TxType.MANDATORY)
	public Object fixEntityForSave(Object entityUI) {
		if(entityUI instanceof BaseEntity) {
			this.doNotReplaceList = ((BaseEntity) entityUI).isDoNotReplaceList(); 
		}
		this.doNotReplaceList = true;
		IgnoreEntitiesForMerge ignoreEntitiesForMerge = entityUI.getClass().getAnnotation(IgnoreEntitiesForMerge.class);
		if(ignoreEntitiesForMerge != null) {
			String[] classes = ignoreEntitiesForMerge.value();
			if(classes != null && classes.length > 0) {
				for(String s : classes) {
					entitiesToIgnoreForMerge.put(s, Boolean.TRUE);
				}
			}
		}
		//boolean isNew = repository.getEntityInformation().isNew(entityUI);
		//Map<String, Object> entityCache = new HashMap<>();
				
		Object entityDB = readEntityFromDB(entityUI);
		if(entityDB != null && entityDB != entityUI) {
			mergeEntity(entityDB, entityUI);
		}
		this.resolveEntities(entityDB, null);
		return entityDB;
	}
	
	private Object readEntityFromDB(Object entity) {
		String idField = "id";
		Object fetchedEntity = entity;
		List<Field>  subFields = FieldUtils.getFieldsListWithAnnotation(entity.getClass(), Id.class);
		if(subFields.size() > 0) {
			idField = subFields.get(0).getName();
		}
		Object refId = BeanUtil.declared.getProperty(entity, idField);
		if(refId instanceof Long) {
			if(entityCache.get(entity.getClass().getSimpleName()+"-"+refId.toString()) != null) {
				fetchedEntity =  entityCache.get(entity.getClass().getSimpleName()+"-"+refId.toString());
			} else {
				Optional data = this.find(entity.getClass(), (Long)refId);
				if(data.isPresent()) {
					fetchedEntity = data.get();
					entityCache.put(entity.getClass().getSimpleName()+"-"+refId.toString(), fetchedEntity);
				}	
			}
		}
		return fetchedEntity;
	}

	@Transactional(value=TxType.MANDATORY)
	private void mergeEntity(Object destination, Object source) {
		if (destination != null && source != null) {
			
			if(lookingAt.stream().anyMatch(o->o == destination)) {
                return; // recursion detected
            }
            lookingAt.add(destination);
            
			List<Field> fields = getFieldsUpTo(destination.getClass(), null);
			if(fields != null) {
				for(Field field : fields) {
					try {
						Object value = BeanUtil.declaredSilent.getProperty(source, field.getName());
						Object valueDest = BeanUtil.declaredSilent.getProperty(destination, field.getName());
						if(value != null) {
							if(valueDest == null) {
								BeanUtil.declaredSilent.setProperty(destination, field.getName(), value);
							} else {
								if(ClassUtils.isPrimitiveOrWrapper(field.getType()) || field.getType().isSynthetic() || "String".equalsIgnoreCase(field.getType().getSimpleName())) {
									BeanUtil.declaredSilent.setProperty(destination, field.getName(), value);
								} else if(field.getType().isAssignableFrom(List.class) || field.getType().isAssignableFrom(Set.class)) {
									Collection collectionSource = (Collection) value;
									if(!collectionSource.isEmpty()) {
										Optional any = collectionSource.stream().findAny();
										if(any.isPresent()) {
											if(ClassUtils.isPrimitiveOrWrapper(any.get().getClass()) || any.get().getClass().isSynthetic() || "String".equalsIgnoreCase(any.get().getClass().getSimpleName())){
												BeanUtilsBean.getInstance().copyProperty(destination, field.getName(), value);
											} else {
												for(Object collSource : collectionSource) {
													if(collSource != null) {
														Object callDest = findCollValBasedOnId((Collection) valueDest, collSource);
														if(callDest == null) {
															if(((Collection) valueDest).stream().anyMatch(o->o == collSource)) {
												                continue; // recursion detected
												            }
															((Collection) valueDest).add(collSource);
														} else {
															Object isDeleted = BeanUtil.declaredSilent.getProperty(collSource, "intStatus");
															if (isDeleted != null && (Integer) isDeleted == INT_STATUS.DELETED.getValue()) {
																((Collection) valueDest).remove(callDest);
																//this.em.remove(callDest);
															} else {
																mergeEntity(callDest, collSource);
															}
														}
													}
												}
												if(!this.doNotReplaceList) {
													List<Object> toRemove = new ArrayList<>();
													for(Object collDest : (Collection) valueDest) {
														if(collDest != null) {
															Object callSource = findCollValBasedOnIdOrValue((Collection) collectionSource, collDest);
															if(callSource == null) {
																toRemove.add(collDest);
															}
														}
													}
													if(!toRemove.isEmpty()) {
														for(Object rem : toRemove) {
															Annotation[] annts = field.getAnnotations();
															if(annts != null && annts.length > 0) {
																for(Annotation ant : annts) {
																	if(ant instanceof OneToMany) {
																		String mappedBy = ((OneToMany)ant).mappedBy();
																		if(StringUtils.isNotBlank(mappedBy)) {
																			BeanUtil.declaredSilent.setProperty(rem, mappedBy, null);
																		}
																	} else if(ant instanceof ManyToMany) {
																		String mappedBy = ((ManyToMany)ant).mappedBy();
																		if(StringUtils.isNotBlank(mappedBy)) {
																			BeanUtil.declaredSilent.setProperty(rem, mappedBy, null);
																		}
																	}
																}
															}
															((Collection) valueDest).remove(rem);
															if(valueDest instanceof org.hibernate.collection.internal.AbstractPersistentCollection) {
																Serializable snapshot = ((org.hibernate.collection.internal.AbstractPersistentCollection)valueDest).getStoredSnapshot();
																if(snapshot != null && snapshot instanceof Collection) {
																	((Collection)snapshot).remove(rem);
																}
															}
															this.delete(rem);
														}
													}
												}
											}
										}
									}
								} else if(field.getType().isAssignableFrom(Map.class)) {
									Set keySet = ((Map) value).keySet();
									List objsToRemove = new ArrayList();
									if(keySet != null && !keySet.isEmpty()) {
										for(Object key: keySet) {
											Object callSource = ((Map) value).get(key);
											Object callDest = ((Map) valueDest).get(key);
											if(callDest == null) {
												 ((Map) valueDest).put(key, callSource);
											} else {
												Object isDeleted = BeanUtil.declaredSilent.getProperty(callSource, "intStatus");
												if (isDeleted != null && (Integer) isDeleted == INT_STATUS.DELETED.getValue()) {
													objsToRemove.add(key);
												} else {
													mergeEntity(callDest, callSource);
												}
											}
										}
										for(Object key : objsToRemove) {
											Object callDest = ((Map) valueDest).get(key);
											((Map) valueDest).remove(key);
											this.delete(callDest);
										}
									}
									if(!this.doNotReplaceList) {
										Set keySetDest = ((Map) valueDest).keySet();
										List<Object> toRemove = new ArrayList<>();
										for(Object key : keySetDest) {
											Object callSource = ((Map) value).get(key);
											Object callDest = ((Map) valueDest).get(key);
											if(callDest != null && callSource == null) {
												toRemove.add(key);
											}
										}
										if(!toRemove.isEmpty()) {
											for(Object rem : toRemove)
												((Map) valueDest).remove(rem);
										}
									}
								} else if(value instanceof BaseEntity || field.getType().isAssignableFrom(BaseEntity.class)) {
									// this is a reference to a different master entity 
									// we should not merge it
									BeanUtil.declaredSilent.setProperty(destination, field.getName(), value);
								}  else if(value instanceof Entity || field.getType().isAssignableFrom(Entity.class)) {
									AlwaysReplaceForMerge alwaysReplaceForMerge = field.getAnnotation(AlwaysReplaceForMerge.class);
									if(alwaysReplaceForMerge != null) {
										BeanUtil.declaredSilent.setProperty(destination, field.getName(), value);
									} else {
										mergeEntity(valueDest, value);
									}
								} else {
									BeanUtilsBean.getInstance().copyProperty(destination, field.getName(), value);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	private Object findCollValBasedOnId(Collection collection, Object sourceColl) {
		Object valFound = null;
		if(collection != null && !collection.isEmpty() && sourceColl != null) {
			String idField = "id";
			Type idType = Long.class;
			List<Field>  subFields = FieldUtils.getFieldsListWithAnnotation(sourceColl.getClass(), Id.class);
			if(subFields.size() > 0) {
				idField = subFields.get(0).getName();
				idType = subFields.get(0).getType();
			}
			//resolves the case like Cdc.ffmCenter && Cdc.cdcAddresses.address
			Object srcId = BeanUtil.declaredSilent.getProperty(sourceColl, idField);
			if(srcId != null) {
				for(Object destColl : collection) {
					if(destColl != null) {
						Object destId = BeanUtil.declaredSilent.getProperty(destColl, idField);
						if(destId != null && destId.toString().equals(srcId.toString())) {
							valFound = destColl;
							break;
						}
					}
				}
			}
		}
		return valFound;
	}
	private Object findCollValBasedOnIdOrValue(Collection collection, Object sourceColl) {
		Object valFound = null;
		if(collection != null && !collection.isEmpty() && sourceColl != null) {

			for(Object destColl : collection) {
				if(destColl != null && destColl == sourceColl) {
					valFound = sourceColl;
				}
			}
			if(valFound == null) {
				String idField = "id";
				Type idType = Long.class;
				List<Field>  subFields = FieldUtils.getFieldsListWithAnnotation(sourceColl.getClass(), Id.class);
				if(subFields.size() > 0) {
					idField = subFields.get(0).getName();
					idType = subFields.get(0).getType();
				}
				//resolves the case like Cdc.ffmCenter && Cdc.cdcAddresses.address
				Object srcId = BeanUtil.declaredSilent.getProperty(sourceColl, idField);
				if(srcId != null) {
					for(Object destColl : collection) {
						if(destColl != null) {
							Object destId = BeanUtil.declaredSilent.getProperty(destColl, idField);
							if(destId != null && destId.toString().equals(srcId.toString())) {
								valFound = destColl;
								break;
							}
						}
					}
				}
			}
		}
		return valFound;
	}

	private List<Field> getFieldsUpTo(@Nonnull Class<?> startClass, @Nullable Class<?> exclusiveParent) {

		List<Field> currentClassFields = Lists.newArrayList(startClass.getDeclaredFields());
		Class<?> parentClass = startClass.getSuperclass();

		if (parentClass != null && (exclusiveParent == null || !(parentClass.equals(exclusiveParent)))) {
			List<Field> parentClassFields = (List<Field>) getFieldsUpTo(parentClass, exclusiveParent);
			currentClassFields.addAll(parentClassFields);
		}

		return currentClassFields;
	}
	
	@Transactional(value=TxType.MANDATORY)
	private void resolveEntities(Object entity, Object parent) {
		if(entity == null) {
			return;
		}
		if(entitiesToIgnoreForMerge.containsKey(entity.getClass().getSimpleName())) {
			return;
		}
		if(lookingAtForMerge.stream().anyMatch(o->o == entity)) {
            return; // recursion detected
        }
		lookingAtForMerge.add(entity);
		
		//handle ManyToOne
		List<Field>  fields = FieldUtils.getFieldsListWithAnnotation(entity.getClass(), ManyToOne.class);
		this.fixEntityForSaveToOneRelations(entity, parent, fields);
		
		//handle OneToOne
		fields = FieldUtils.getFieldsListWithAnnotation(entity.getClass(), OneToOne.class);
		this.fixEntityForSaveToOneRelations(entity, parent, fields);
		
		//handle OneToMany like like Cdc.cdcAddresses
		fields = FieldUtils.getFieldsListWithAnnotation(entity.getClass(), OneToMany.class);
		if(fields != null && fields.size() > 0) {
			for(Field field : fields) {
				Object referenceValueList = BeanUtil.declared.getProperty(entity, field.getName());
				if(referenceValueList != null && referenceValueList instanceof List) {
					for(Object referenceValue : (List)referenceValueList) {
						this.resolveEntities(referenceValue, entity);
					}
				} else if(referenceValueList != null && referenceValueList instanceof Map) {
					for(Entry referenceValueEntry : (Set<Entry>)((Map)referenceValueList).entrySet()) {
						Object referenceValue = referenceValueEntry.getValue();
						this.resolveEntities(referenceValue, entity);
					}
				} else if(referenceValueList != null && referenceValueList instanceof Set) {
					for(Object referenceValue : (Set)referenceValueList) {
						this.resolveEntities(referenceValue, entity);
					}
				}
			}
		}
	}
	
	private List<Field> getAllFieldsListForType(Class clazz, Class type){
		List<Field>  fieldsFotTypeReturn = new ArrayList<>();
		List<Field>  fieldsFotType = FieldUtils.getAllFieldsList(clazz);
		if(fieldsFotType != null && !fieldsFotType.isEmpty()) {
			for(Field fld : fieldsFotType) {
				if(fld != null && fld.getType().equals(type)) {
					fieldsFotTypeReturn.add(fld);
				}
			}
		}

		return fieldsFotTypeReturn;
	}
	
	private void fixEntityForSaveToOneRelations(Object entity, Object parent, List<Field>  fields) {
		for(Field field : fields) {
			Object referenceValue = BeanUtil.declared.getProperty(entity, field.getName());
			if(referenceValue != null) {
				String idField = "id";
				Type idType = Long.class;
				List<Field>  subFields = FieldUtils.getFieldsListWithAnnotation(referenceValue.getClass(), Id.class);
				if(subFields.size() > 0) {
					idField = subFields.get(0).getName();
					idType = subFields.get(0).getType();
				}
				//resolves the case like Cdc.ffmCenter && Cdc.cdcAddresses.address
				Object refId = BeanUtil.declared.getProperty(referenceValue, idField);
				//LOG.error("Field "+field.getName()+" Id "+refId);
				if(refId != null) {
					if(StringUtils.isNumeric(refId.toString()) && Long.parseLong(refId.toString())==0) {
						BeanUtil.declared.setProperty(entity, field.getName(), null);
					} else {
						//BaseRepository baseRepo = baseCRUDService.getBaseRepoForField(field);
						//if(baseRepo != null) {
							Object fetched = null;
							boolean alreadyParsed = false;
							if(entityCache.get(field.getType().getSimpleName()+"-"+refId.toString()) != null) {
								fetched = entityCache.get(field.getType().getSimpleName()+"-"+refId.toString());
								alreadyParsed = true;
							} else {
								fetched = em.find(field.getType(), refId);
								if(fetched != null) {
									entityCache.put(field.getType().getSimpleName()+"-"+refId.toString(), fetched);
								}
							}
							if(fetched != null && !alreadyParsed) {
								if(entitiesToIgnoreForMerge.containsKey(fetched.getClass().getSimpleName())) {
									continue;
								}
								//BeanUtils.copyProperties(referenceValue, fetched);
								if(referenceValue != fetched) {
									mergeEntity(fetched, referenceValue);
								}
								BeanUtil.declared.setProperty(entity, field.getName(), fetched);
								this.resolveEntities(fetched, entity);
							} else if(fetched != null && alreadyParsed) {
								BeanUtil.declared.setProperty(entity, field.getName(), fetched);
							}
						//}
					}
				}
			} else {
				// if reference to parent like Cdc.cdcAddresses.cdc
				List<Field>  fieldsFotType = getAllFieldsListForType(entity.getClass(), field.getType());
				if(parent != null && field.getType().isAssignableFrom(parent.getClass()) && fieldsFotType.size() == 1) {
					BeanUtil.declared.setProperty(entity, field.getName(), parent);
				} else if(parent != null && field.getType().isAssignableFrom(parent.getClass()) && fieldsFotType.size() > 1) {
					List<Field>  parentFields = FieldUtils.getFieldsListWithAnnotation(entity.getClass(), ParentEntityReference.class); 
					if(parentFields != null && !parentFields.isEmpty()) {
						for(Field parentField : parentFields) {
							if(parentFields != null && parentField.getType().isAssignableFrom(parent.getClass())) {
								BeanUtil.declared.setProperty(entity, parentField.getName(), parent);
							}
						}
					}
				}
			}
		}
	}
	
	private void delete(Object entity) {
		Assert.notNull(entity, "The entity must not be null!");
		this.em.remove(this.em.contains(entity) ? entity : this.em.merge(entity));
	}
	
	private Optional find(Class dataModelClass, Long id) {
		Object t = em.find(dataModelClass, id);
		return Optional.ofNullable(t);
    }
}

