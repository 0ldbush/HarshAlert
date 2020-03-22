package com.alnt.platform.base.request;

import java.util.ArrayList;
import java.util.List;

public class Criteria {
	private String fieldName;
    private String operator = "=";
    private String value;
    private List<Object> valueList;
    private boolean isAnd = true;
    private boolean isDistinct=false;
    private List<Criteria> nestedCriteria = new ArrayList<Criteria>();
    
    public Criteria() {
		super();
	}
	public Criteria(String fieldName, String value) {
		super();
		this.fieldName = fieldName;
		this.value = value;
	}
	public Criteria(String fieldName, String operation, String value) {
		super();
		this.fieldName = fieldName;
		this.operator = operation;
		this.value = value;
	}
	public Criteria(String fieldName, String operation, List<Object> valueList) {
		super();
		this.fieldName = fieldName;
		this.operator = operation;
		this.valueList = valueList;
	}
	
	public Criteria(String fieldName, String operation, String value, boolean isAnd) {
		super();
		this.fieldName = fieldName;
		this.operator = operation;
		this.value = value;
		this.isAnd = isAnd;
	}
	
	
	public boolean isDistinct() {
		return isDistinct;
	}
	public void setDistinct(boolean isDistinct) {
		this.isDistinct = isDistinct;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operation) {
		this.operator = operation;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public List<Object> getValueList() {
		return valueList;
	}
	public void setValueList(List<Object> valueList) {
		this.valueList = valueList;
	}
	public boolean isAnd() {
		return isAnd;
	}
	public void setAnd(boolean isAnd) {
		this.isAnd = isAnd;
	}
	public List<Criteria> getNestedCriteria() {
		return nestedCriteria;
	}
	public void setNestedCriteria(List<Criteria> nestedCriteria) {
		this.nestedCriteria = nestedCriteria;
	}
	public void addNestedCriteria(Criteria nestedCriteria) {
		this.nestedCriteria.add(nestedCriteria);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + (isAnd ? 1231 : 1237);
		result = prime * result + ((nestedCriteria == null) ? 0 : nestedCriteria.hashCode());
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criteria other = (Criteria) obj;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (isAnd != other.isAnd)
			return false;
		if (nestedCriteria == null) {
			if (other.nestedCriteria != null)
				return false;
		} else if (!nestedCriteria.equals(other.nestedCriteria))
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Criteria [fieldName=" + fieldName + ", operator=" + operator + ", value=" + value + ", valueList="
				+ valueList + ", isAnd=" + isAnd + ", nestedCriteria=" + nestedCriteria + "]";
	}  
    
}
