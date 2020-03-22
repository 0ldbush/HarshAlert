package com.alnt.platform.base.request;

public class SelectionField {
	
	private String fieldName;
	
	private String displayName;
	
	private String aliasName;
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getDisplayName() {
		return displayName==null?fieldName:displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	

}
