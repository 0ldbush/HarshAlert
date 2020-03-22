package com.alnt.platform.core.apiservice.service.type;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alnt.platform.base.persistence.EnumerationType;

/**
 * An extendible enumeration of product types.
 * 
 */
public class ApiServiceType implements Serializable, EnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, ApiServiceType> TYPES = new LinkedHashMap<String, ApiServiceType>();

    public static final ApiServiceType METHOD  = new ApiServiceType("METHOD", "Method");
    public static final ApiServiceType KAFKA_EVENTS  = new ApiServiceType("KAFKA_EVENTS", "Kafka Events");
    public static final ApiServiceType BUSINESS_RULE  = new ApiServiceType("BUSINESS_RULE", "Business Rule");

    public static ApiServiceType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String text;

    public ApiServiceType() {
        //do nothing
    }

    public ApiServiceType(final String type, final String friendlyType) {
        this.text = friendlyType;
        setType(type);
    }

    public String getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!getClass().isAssignableFrom(obj.getClass()))
            return false;
        ApiServiceType other = (ApiServiceType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
