package com.alnt.workflow.service.type;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alnt.platform.base.persistence.EnumerationType;

/**
 * An extendible enumeration of product types.
 * 
 */
public class WorkflowStepParallelTasksType implements Serializable, EnumerationType {

    private static final long serialVersionUID = 1L;

    private static final Map<String, WorkflowStepParallelTasksType> TYPES = new LinkedHashMap<String, WorkflowStepParallelTasksType>();

    public static final WorkflowStepParallelTasksType OWNER  = new WorkflowStepParallelTasksType("OWNER", "Based on Owner");
    public static final WorkflowStepParallelTasksType SUBDOC  = new WorkflowStepParallelTasksType("SUBDOC", "Based on a sub-document");
    public static final WorkflowStepParallelTasksType SUCCESSOR  = new WorkflowStepParallelTasksType("SUCCESSOR", "Based on successor steps");

    public static WorkflowStepParallelTasksType getInstance(final String type) {
        return TYPES.get(type);
    }

    private String type;
    private String text;

    public WorkflowStepParallelTasksType() {
        //do nothing
    }

    public WorkflowStepParallelTasksType(final String type, final String friendlyType) {
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
        WorkflowStepParallelTasksType other = (WorkflowStepParallelTasksType) obj;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
}
