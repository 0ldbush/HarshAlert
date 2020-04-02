package com.alnt.platform.base.repository;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ELEMENT_COLLECTION;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.MANY_TO_ONE;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_MANY;
import static javax.persistence.metamodel.Attribute.PersistentAttributeType.ONE_TO_ONE;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.Attribute.PersistentAttributeType;
import javax.persistence.metamodel.Bindable;
import javax.persistence.metamodel.ManagedType;
import javax.persistence.metamodel.PluralAttribute;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mapping.PropertyPath;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.persistence.EntityPersistFixer;
import com.alnt.platform.base.persistence.QuerySpecification;
import com.alnt.platform.base.persistence.Specification;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.typesafe.config.ConfigFactory;

public abstract class BaseRepositoryImpl<E extends Entity> implements BaseRepository<E> {
	final Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	protected final JPAApi jpaApi;
    protected final DatabaseExecutionContext executionContext;
    protected final Class<E> dataModelClass;
    private static final Map<PersistentAttributeType, Class<? extends Annotation>> ASSOCIATION_TYPES;
    private EntityManager defaultEmf = null;
    private EntityManager timelineEmf = null;
    
    static {

		Map<PersistentAttributeType, Class<? extends Annotation>> persistentAttributeTypes = new HashMap<PersistentAttributeType, Class<? extends Annotation>>();
		persistentAttributeTypes.put(ONE_TO_ONE, OneToOne.class);
		persistentAttributeTypes.put(ONE_TO_MANY, null);
		persistentAttributeTypes.put(MANY_TO_ONE, ManyToOne.class);
		persistentAttributeTypes.put(MANY_TO_MANY, null);
		persistentAttributeTypes.put(ELEMENT_COLLECTION, null);

		ASSOCIATION_TYPES = Collections.unmodifiableMap(persistentAttributeTypes);

	}
    
//    private final CircuitBreaker<Optional<PostData>> circuitBreaker = new CircuitBreaker<Optional<PostData>>().withFailureThreshold(1).withSuccessThreshold(3);

    
    public BaseRepositoryImpl(JPAApi jpaApi, DatabaseExecutionContext executionContext, Class<E> dataModelClass) {
        this.jpaApi = jpaApi;
        this.executionContext = executionContext;
        this.dataModelClass = dataModelClass;
    }
    
	public Class<E> getDomainClass() {
		return this.dataModelClass;
	}
	
	public EntityManager getEmfForTenant(RequestDetails requestDetails) {
		return jpaApi.em(requestDetails.getTenantName());
	}
	
	
	@Override
	public CompletionStage<Stream<E>> list(RequestDetails requestDetails) {
		return supplyAsync(() -> list(getEmfForTenant(requestDetails)), executionContext);
	}
	
	@Override
	public CompletionStage<Page<E>> findAll(RequestDetails requestDetails, SearchCriteria searchParams) {
		return supplyAsync(() -> wrap(requestDetails, em -> findAll(requestDetails, em, searchParams)), executionContext);
	}

//	@Override
//	public CompletionStage<E> create(RequestDetails requestDetails, E data) {
//		return supplyAsync(() -> wrap(requestDetails, em -> insert(requestDetails, em, data)), executionContext);
//	}

	@Override
	public CompletionStage<Optional<E>> get(RequestDetails requestDetails, Long id) {
		//return supplyAsync(() -> lookup(getEmfForTenant(requestDetails), id), executionContext);
		return supplyAsync(() -> wrap(requestDetails, em -> lookup( em, id)), executionContext);
	}

	@Override
	public CompletionStage<List<E>> getBy(RequestDetails requestDetails, String fieldName, Object value) {
		//return supplyAsync(() -> lookupBy(getEmfForTenant(requestDetails), fieldName, value), executionContext);
		return supplyAsync(() -> wrap(requestDetails, em -> lookupBy( em, fieldName, value)), executionContext);
	}

//	@Override
//	public CompletionStage<Optional<E>> update(RequestDetails requestDetails, Long id, E postData) {
//		return supplyAsync(() -> wrap(requestDetails, em -> modify(requestDetails, em, id, postData)), executionContext);
//	}
	
	@Override
	public CompletionStage<Optional<E>> save(RequestDetails requestDetails, E postData) {
		return supplyAsync(() -> wrap(requestDetails, em -> save(requestDetails, em, postData)), executionContext);
	}
	
	@Override
	public CompletionStage<List<Optional<E>>> saveAll(RequestDetails requestDetails, List<E> postData) {
		return supplyAsync(() -> saveInBatch(requestDetails, postData), executionContext);
	}
	
	@Override
	public CompletionStage<Optional> getGenericObjectById(RequestDetails requestDetails, Class clazz, Long id) {
		return supplyAsync(() -> wrap(requestDetails, em -> lookupGeneric(em, clazz, id)), executionContext);
	}
	
	@Override
	public CompletionStage<List> getByGeneric(RequestDetails requestDetails, Class clazz, String fieldName, Object value) {
		//return supplyAsync(() -> lookupByGeneric(getEmfForTenant(requestDetails), clazz, fieldName, value), executionContext);
		return supplyAsync(() -> wrap(requestDetails, em -> lookupByGeneric(em, clazz, fieldName, value)), executionContext);
	}

	
	@Override
	public CompletionStage<Optional> saveGeneric(RequestDetails requestDetails, Object postData) {
		return supplyAsync(() -> wrap(requestDetails, em -> saveGeneric(requestDetails, em, postData)), executionContext);
	}
	
	
	private void setChangedFlags(RequestDetails requestDetails, E entity) {
		if(entity instanceof BaseEntity) {
			BaseEntity bEntity = (BaseEntity) entity;
			Date date = new Date();
			if(bEntity.getCreatedOn() == null) {
				bEntity.setCreatedOn(date);
				if(requestDetails != null && requestDetails.getUser() != null) {
					bEntity.setCreatedBy(requestDetails.getUser().getId());
				}
			}
			if(bEntity.getIntStatus() == null) {
				bEntity.setIntStatus(BaseEntity.INT_STATUS.ACTIVE.getValue());
			}
			bEntity.setChangedOn(date);
			if(requestDetails != null && requestDetails.getUser() != null) {
				bEntity.setChangedBy(requestDetails.getUser().getId());
			}
			
		}
	}
	
	private void setChangedFlags(RequestDetails requestDetails, List<E> entityList) {
		for(E entity: entityList)
		{
		if(entity instanceof BaseEntity) {
			BaseEntity bEntity = (BaseEntity) entity;
			Date date = new Date();
			if(bEntity.getCreatedOn() == null) {
				bEntity.setCreatedOn(date);
				bEntity.setIntStatus(BaseEntity.INT_STATUS.ACTIVE.getValue());
				if(requestDetails != null && requestDetails.getUser() != null) {
					bEntity.setCreatedBy(requestDetails.getUser().getId());
				}
			}
			bEntity.setChangedOn(date);
			if(requestDetails != null && requestDetails.getUser() != null) {
				bEntity.setChangedBy(requestDetails.getUser().getId());
			}
			
		}
		}
	}

	
	protected <E> E wrap(RequestDetails requestDetails, Function<EntityManager, E> function) {
		E e = jpaApi.withTransaction(requestDetails.getTenantName(), function);
//		jpaApi.em(requestDetails.getTenantName()).refresh(e);
		return e;
    }
	
	private Stream<E> list(EntityManager em) {
        List<E> data = em.createQuery("select d from "+this.dataModelClass.getSimpleName()+" d", this.dataModelClass).getResultList();
        return data.stream();
    }
	
//	private E insert(RequestDetails requestDetails, EntityManager em, E person) {
//    	setChangedFlags(requestDetails, person);
//        em.persist(person);
//        return person;
//    }
	
	private Optional lookup(EntityManager em, Long id) {
		Object t = em.find(this.dataModelClass, id);
		return Optional.ofNullable(t);
    }
	
	private List<E> lookupBy(EntityManager em, String fieldName, Object value) {
		
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery<E> q = cb.createQuery(getDomainClass());
		Root<E> c = q.from(getDomainClass());
		ParameterExpression<Object> p = cb.parameter(Object.class);
		q.select(c).where(cb.equal(c.get(fieldName), p));
		
		TypedQuery<E> query = em.createQuery(q);
		query.setParameter(p, value);
		return query.getResultList();
    }
	
	private List lookupByGeneric(EntityManager em, Class clazz, String fieldName, Object value) {
		CriteriaBuilder cb = em.getCriteriaBuilder();

		CriteriaQuery q = cb.createQuery(clazz);
		Root c = q.from(clazz);
		ParameterExpression<Object> p = cb.parameter(Object.class);
		q.select(c).where(cb.equal(c.get(fieldName), p));
		
		TypedQuery<E> query = em.createQuery(q);
		query.setParameter(p, value);
		return query.getResultList();
    }
	
	@SuppressWarnings("unused")
	private void delete(EntityManager em, E entity) {
		Assert.notNull(entity, "The entity must not be null!");
		em.remove(em.contains(entity) ? entity : em.merge(entity));
	}
	
//	private Optional<E> modify(RequestDetails requestDetails, EntityManager em, Long id, E postData) {
//        final E data = (E) em.find(this.dataModelClass, id);
//        if (data != null) {
//			/*
//			 * if(postData instanceof Poster) {
//			 * ((Poster)data).setUrl(((Poster)postData).getUrl());
//			 * ((Poster)data).setFabricJson(((Poster)postData).getFabricJson()); }
//			 */
//        	return Optional.ofNullable(em.merge(data));
//        }
//        return null;
//    }
	
	private Optional<E> save(RequestDetails requestDetails, EntityManager em, E entity) {
        if (entity != null) {
        	entity = (E) new EntityPersistFixer(em).fixEntityForSave(entity);
        	setChangedFlags(requestDetails, entity);
        	entity = em.merge(entity);
        	//em.refresh(entity);
    		//if (entityInformation.isNew(entity)) {
    			//em.persist(entity);
    			//return Optional.ofNullable(entity);
    		//} else {
    			return Optional.ofNullable(entity);
    		//}
        }
        return null;
    }
	
	private List<Optional<E>> saveInBatch(RequestDetails requestDetails, List<E> entities) {
		int batchSize =ConfigFactory.load().getInt("play.jpa.batchSize");
		List<Optional<E>> savedEnties=new ArrayList<Optional<E>>();
		
		EntityManager entityManager = null;
	    EntityTransaction tx = null;
	    try {
	    	String name=requestDetails.getTenantName();
	        entityManager = jpaApi.em(name);
	       
	        if (entityManager == null) {
	          throw new RuntimeException("Could not create JPA entity manager for '" + name + "'");
	        }
	        entityManager.setFlushMode(FlushModeType.COMMIT);
	          tx = entityManager.getTransaction();
	          tx.begin();

	          int length=entities.size();
	          for(int i=0;i<length;i++)
	  		{
	  			Optional<E> saved=	save(requestDetails, entityManager, entities.get(i));
	  			savedEnties.add(saved);
	  			if((i+1)%batchSize==0 && i>0)
	  			{
	  				entityManager.flush();
	  				entityManager.clear();
	  				 tx.commit();
	  				tx.begin();
	  			}
	  		}
	          
	        if (tx != null) {
	          if (tx.getRollbackOnly()) {
	            tx.rollback();
	          } else {
	            tx.commit();
	          }
	        }

	        return savedEnties;

	      } catch (Throwable t) {
	        if (tx != null) {
	          try {
	            if (tx.isActive()) {
	              tx.rollback();
	              
	            }
	          } catch (Exception e) {
	            Logger.error("Could not rollback transaction", e);
	          }
	        }
	        throw t;
	      } finally {
	        if (entityManager != null) {
	          entityManager.close();
	        }
	      }

		}
		
	
	private Optional saveGeneric(RequestDetails requestDetails, EntityManager em, Object entity) {
        if (entity != null) {
        	entity =  new EntityPersistFixer(em).fixEntityForSave(entity);
    		//if (entityInformation.isNew(entity)) {
    			//em.persist(entity);
    			//return Optional.ofNullable(entity);
    		//} else {
    			return Optional.ofNullable(em.merge(entity));
    		//}
        }
        return null;
    }
	
	private Optional lookupGeneric(EntityManager em, Class clazz, Long id) {
		Object t = em.find(clazz, id);
		return Optional.ofNullable(t);
    }
	
	public Page<E> findAll(RequestDetails requestDetails, EntityManager em, SearchCriteria searchParams){
		QuerySpecification<E> spec = SearchCriteria.buildQuerySpecification(searchParams);
		Pageable pageRequest = searchParams != null ? searchParams.getPageable() : null;
		Page<E> results = null;
		if(spec != null) {
			results = findAll(em, spec, pageRequest);
		} else {
			results = findAll(em, pageRequest);
		}
		return results;
	}
	

	public Page<E> findAll(EntityManager em, Pageable pageable) {
		if (isUnpaged(pageable)) {
			return new PageImpl<E>(findAll(em));
		}
		return findAll(em,(Specification<E>) null, pageable);
	}
	
	public List<E> findAll(EntityManager em) {
		return getQuery(em, null, Sort.unsorted()).getResultList();
	}
	
	public Page<E> findAll(EntityManager em, @Nullable Specification<E> spec, Pageable pageable) {
		TypedQuery<E> query = getQuery(em, spec, pageable);
		return isUnpaged(pageable) ? new PageImpl<E>(query.getResultList())
				: readPage(em, query, getDomainClass(), pageable, spec);
	}
	
	private static boolean isUnpaged(Pageable pageable) {
		return pageable.isUnpaged();
	}
	
	protected TypedQuery<E> getQuery(EntityManager em, @Nullable Specification<E> spec, Sort sort) {
		return getQuery(em, spec, getDomainClass(), sort);
	}
	
	protected TypedQuery<E> getQuery(EntityManager em, @Nullable Specification<E> spec, Pageable pageable) {

		Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
		return getQuery(em, spec, getDomainClass(), sort);
	}
	
	protected <S extends E> TypedQuery<S> getQuery(EntityManager em, @Nullable Specification<S> spec, Class<S> domainClass,
			Pageable pageable) {

		Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
		return getQuery(em, spec, domainClass, sort);
	}
	
	protected <S extends E> TypedQuery<S> getQuery(EntityManager em, @Nullable Specification<S> spec, Class<S> domainClass, Sort sort) {
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<S> criteriaQuery = builder.createQuery(domainClass);

		Root<S> root = applySpecificationToCriteria(em, spec, domainClass, criteriaQuery);
		criteriaQuery.select(root);

		if (sort.isSorted()) {
			criteriaQuery.orderBy(toOrders(sort, root, builder));
		}

		//TypedQuery<S> query =  applyRepositoryMethodMetadata(em.createQuery(criteriaQuery));
		TypedQuery<S> query =  em.createQuery(criteriaQuery);
		
		if(spec != null && spec instanceof QuerySpecification) {
			String asociationNames = ((QuerySpecification)spec).getAsociationNames();
			if(asociationNames != null && asociationNames.length() > 0) {
				String[] assocs = asociationNames.split(",");
				EntityGraph graph = em.createEntityGraph(domainClass);
				for(String assoc : assocs) {
					graph.addSubgraph(assoc);
				}
				query.setHint("javax.persistence.loadgraph", graph);
			}
		}
		return query;
	}
	
	protected <S extends E> Page<S> readPage(EntityManager em, TypedQuery<S> query, final Class<S> domainClass, Pageable pageable,
			@Nullable Specification<S> spec) {

		if (pageable.isPaged()) {
			query.setFirstResult((int) pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}

		return PageableExecutionUtils.getPage(query.getResultList(), pageable,
				() -> executeCountQuery(getCountQuery(em, spec, domainClass)));
	}
	
	protected <S extends E> TypedQuery<Long> getCountQuery(EntityManager em, @Nullable Specification<S> spec, Class<S> domainClass) {

		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);

		Root<S> root = applySpecificationToCriteria(em, spec, domainClass, query);

		if (query.isDistinct()) {
			query.select(builder.countDistinct(root));
		} else {
			query.select(builder.count(root));
		}

		// Remove all Orders the Specifications might have applied
		query.orderBy(Collections.<javax.persistence.criteria.Order> emptyList());

		return em.createQuery(query);
	}
	
	private <S, U extends E> Root<U> applySpecificationToCriteria(EntityManager em,@Nullable Specification<U> spec, Class<U> domainClass,
			CriteriaQuery<S> query) {

		Assert.notNull(domainClass, "Domain class must not be null!");
		Assert.notNull(query, "CriteriaQuery must not be null!");

		Root<U> root = query.from(domainClass);

		if (spec == null) {
			return root;
		}

		CriteriaBuilder builder = em.getCriteriaBuilder();
		Predicate predicate = spec.toPredicate(root, query, builder);

		if (predicate != null) {
			query.where(predicate);
		}

		return root;
	}
	
	private static Long executeCountQuery(TypedQuery<Long> query) {

		Assert.notNull(query, "TypedQuery must not be null!");

		List<Long> totals = query.getResultList();
		Long total = 0L;

		for (Long element : totals) {
			total += element == null ? 0 : element;
		}

		return total;
	}
	
	public static List<javax.persistence.criteria.Order> toOrders(Sort sort, From<?, ?> from, CriteriaBuilder cb) {

		if (sort.isUnsorted()) {
			return Collections.emptyList();
		}

		Assert.notNull(from, "From must not be null!");
		Assert.notNull(cb, "CriteriaBuilder must not be null!");

		List<javax.persistence.criteria.Order> orders = new ArrayList<>();

		for (org.springframework.data.domain.Sort.Order order : sort) {
			orders.add(toJpaOrder(order, from, cb));
		}

		return orders;
	}
	
	@SuppressWarnings("unchecked")
	private static javax.persistence.criteria.Order toJpaOrder(Order order, From<?, ?> from, CriteriaBuilder cb) {

		PropertyPath property = PropertyPath.from(order.getProperty(), from.getJavaType());
		Expression<?> expression = toExpressionRecursively(from, property);

		if (order.isIgnoreCase() && String.class.equals(expression.getJavaType())) {
			Expression<String> lower = cb.lower((Expression<String>) expression);
			return order.isAscending() ? cb.asc(lower) : cb.desc(lower);
		} else {
			return order.isAscending() ? cb.asc(expression) : cb.desc(expression);
		}
	}
	
	static <T> Expression<T> toExpressionRecursively(From<?, ?> from, PropertyPath property) {

		Bindable<?> propertyPathModel;
		Bindable<?> model = from.getModel();
		String segment = property.getSegment();

		if (model instanceof ManagedType) {

			/*
			 *  Required to keep support for EclipseLink 2.4.x. TODO: Remove once we drop that (probably Dijkstra M1)
			 *  See: https://bugs.eclipse.org/bugs/show_bug.cgi?id=413892
			 */
			propertyPathModel = (Bindable<?>) ((ManagedType<?>) model).getAttribute(segment);
		} else {
			propertyPathModel = from.get(segment).getModel();
		}

		if (requiresJoin(propertyPathModel, model instanceof PluralAttribute, !property.hasNext()) && !isAlreadyFetched(from, segment)) {
			Join<?, ?> join = getOrCreateJoin(from, segment);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(join, property.next()) : join);
		} else {
			Path<Object> path = from.get(segment);
			return (Expression<T>) (property.hasNext() ? toExpressionRecursively(path, property.next()) : path);
		}
	}
	
	static Expression<Object> toExpressionRecursively(Path<Object> path, PropertyPath property) {

		Path<Object> result = path.get(property.getSegment());
		return property.hasNext() ? toExpressionRecursively(result, property.next()) : result;
	}
	
	private static boolean requiresJoin(@Nullable Bindable<?> propertyPathModel, boolean isPluralAttribute,
			boolean isLeafProperty) {

		if (propertyPathModel == null && isPluralAttribute) {
			return true;
		}

		if (!(propertyPathModel instanceof Attribute)) {
			return false;
		}

		Attribute<?, ?> attribute = (Attribute<?, ?>) propertyPathModel;

		if (!ASSOCIATION_TYPES.containsKey(attribute.getPersistentAttributeType())) {
			return false;
		}

		if (isLeafProperty && !attribute.isCollection()) {
			return false;
		}

		Class<? extends Annotation> associationAnnotation = ASSOCIATION_TYPES.get(attribute.getPersistentAttributeType());

		if (associationAnnotation == null) {
			return true;
		}

		Member member = attribute.getJavaMember();

		if (!(member instanceof AnnotatedElement)) {
			return true;
		}

		Annotation annotation = AnnotationUtils.getAnnotation((AnnotatedElement) member, associationAnnotation);
		return annotation == null ? true : (boolean) AnnotationUtils.getValue(annotation, "optional");
	}
	
	private static Join<?, ?> getOrCreateJoin(From<?, ?> from, String attribute) {

		for (Join<?, ?> join : from.getJoins()) {

			boolean sameName = join.getAttribute().getName().equals(attribute);

			if (sameName && join.getJoinType().equals(JoinType.LEFT)) {
				return join;
			}
		}

		return from.join(attribute, JoinType.LEFT);
	}
	
	private static boolean isAlreadyFetched(From<?, ?> from, String attribute) {

		for (Fetch<?, ?> fetch : from.getFetches()) {

			boolean sameName = fetch.getAttribute().getName().equals(attribute);

			if (sameName && fetch.getJoinType().equals(JoinType.LEFT)) {
				return true;
			}
		}

		return false;
	}
	
	@Override
	public CompletionStage<String> delete(RequestDetails requestDetails, Long id) {
	return	supplyAsync(()->{
	return	wrap(requestDetails, em -> {
		Optional<E> dd=	lookup(getEmfForTenant(requestDetails), id);
		delete(em, dd.get());
		return "Data Deleted Successfully";
			});
	
		},executionContext);
	
	}
}
