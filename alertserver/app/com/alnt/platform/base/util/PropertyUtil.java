package com.alnt.platform.base.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class PropertyUtil {

	public static Object getPropertyValue(Object object, String propertyName)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String[] components = propertyName.split("\\.");
		return getPropertyValueInternal(object, components, 0);
	}

	private static Object getPropertyValueInternal(Object object, String[] components, int currentPosition)
			throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		if (object == null) {
			return null;
		}

		boolean isPropertyReadable = PropertyUtils.isReadable(object, components[currentPosition]);
		if (!isPropertyReadable) {
			// LOG.debug(String.format("Could not find %s on %s, assuming this exists
			// elsewhere in the class hierarchy",
			// StringUtil.sanitize(components[currentPosition]),
			// object.getClass().getName()));
			return null;
		}

		Object propertyObject = PropertyUtils.getProperty(object, components[currentPosition]);

		if (propertyObject != null) {
			if (currentPosition < components.length - 1) {
				if (Collection.class.isAssignableFrom(propertyObject.getClass())) {
					Collection<?> collection = (Collection<?>) propertyObject;
					HashSet<Object> newCollection = new HashSet<Object>();
					for (Object item : collection) {
						Object result = getPropertyValueInternal(item, components, currentPosition + 1);
						if (result != null) {
							copyPropertyToCollection(newCollection, result);
						}
					}
					propertyObject = newCollection;
				} else if (Map.class.isAssignableFrom(propertyObject.getClass())) {
					Map<?, ?> map = (Map<?, ?>) propertyObject;
					HashSet<Object> newCollection = new HashSet<Object>();
					for (Object item : map.values()) {
						Object result = getPropertyValueInternal(item, components, currentPosition + 1);
						if (result != null) {
							copyPropertyToCollection(newCollection, result);
						}
					}
					propertyObject = newCollection;
				} else if (propertyObject.getClass().isArray()) {
					Object[] array = (Object[]) propertyObject;
					HashSet<Object> newCollection = new HashSet<Object>();
					for (Object item : array) {
						Object result = getPropertyValueInternal(item, components, currentPosition + 1);
						if (result != null) {
							copyPropertyToCollection(newCollection, result);
						}
					}
					propertyObject = newCollection;
				} else {
					propertyObject = getPropertyValueInternal(propertyObject, components, currentPosition + 1);
				}
			}
		}

		return propertyObject;
	}

	public static void copyPropertyToCollection(Collection<Object> collection, Object o) {
		if (o == null) {
			return;
		}

		if (Collection.class.isAssignableFrom(o.getClass())) {
			collection.addAll((Collection<?>) o);
		} else if (Map.class.isAssignableFrom(o.getClass())) {
			collection.addAll(((Map<?, ?>) o).values());
		} else if (o.getClass().isArray()) {
			Object[] array = (Object[]) o;
			if (array.length > 0) {
				for (Object obj : array) {
					collection.add(obj);
				}
			}
		} else {
			collection.add(o);
		}
	}

}
