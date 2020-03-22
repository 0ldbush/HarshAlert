package com.alnt.platform.base.persistence;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.Id;
import org.apache.commons.collections.MapUtils;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.TableGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class IdOverrideTableGenerator extends TableGenerator {

	public static final String ENTITY_NAME_PARAM = "entity_name";

	public static final String DEFAULT_TABLE_NAME = "SEQUENCE_GENERATOR";
	public static final String DEFAULT_SEGMENT_COLUMN_NAME = "ID_NAME";
	public static final String DEFAULT_VALUE_COLUMN_NAME = "ID_VAL";
	public static final int DEFAULT_INCREMENT_SIZE = 50;

	@SuppressWarnings("unchecked")
	private static final Map<String, Field> FIELD_CACHE = MapUtils.synchronizedMap(new HashMap<String, Field>());

	private String entityName;

	private Field getIdField(Class<?> clazz) {
		Field response = null;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.getAnnotation(Id.class) != null) {
				response = field;
				break;
			}
		}
		if (response == null && clazz.getSuperclass() != null) {
			response = getIdField(clazz.getSuperclass());
		}
		return response;
	}

	@Override
	public Serializable generate(final SharedSessionContractImplementor session, final Object obj) {
		/*
		 * This works around an issue in Hibernate where if the entityPersister is
		 * retrieved from the session and used to get the Id, the entity configuration
		 * can be recycled, which is messing with the load persister and current
		 * persister on some collections. This may be a jrebel thing, but this
		 * workaround covers all environments
		 */
		String objName = obj.getClass().getName();
		if (!FIELD_CACHE.containsKey(objName)) {
			Field field = getIdField(obj.getClass());
			if (field == null) {
				throw new IllegalArgumentException("Cannot specify IdOverrideTableGenerator for an entity (" + objName
						+ ") that does not have an Id field declared using the @Id annotation.");
			}
			field.setAccessible(true);
			FIELD_CACHE.put(objName, field);
		}
		Field field = FIELD_CACHE.get(objName);
		final Serializable id;
		try {
			id = (Serializable) field.get(obj);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		if (id != null) {
			return id;
		}
		return super.generate(session, obj);
	}

	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		if (params.get("table_name") == null) {
			params.put("table_name", "SEQUENCE_GENERATOR");
		}

		if (params.get("segment_column_name") == null) {
			params.put("segment_column_name", DEFAULT_SEGMENT_COLUMN_NAME);
		}

		if (params.get("value_column_name") == null) {
			params.put("value_column_name", DEFAULT_VALUE_COLUMN_NAME);
		}

		if (params.get("increment_size") == null) {
			params.put("increment_size", DEFAULT_INCREMENT_SIZE);
		}
		super.configure(type, params, serviceRegistry);
		entityName = (String) params.get(ENTITY_NAME_PARAM);
	}

	public String getEntityName() {
		return entityName;
	}

	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
}
