package com.alnt.platform.base.request;

import org.apache.commons.lang3.StringUtils;

public class JoinEntity {
	
	private String entityName;
	private String condition;
	private String aliasName;
	
	
	public JoinEntity() {
		super();		
	}
	public JoinEntity(String entityName, String condition, String aliasName) {
		super();
		this.entityName = entityName;
		this.condition = condition;
		this.aliasName = aliasName;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = (StringUtils.isNotBlank(aliasName))?aliasName:entityName.toLowerCase();
	}
	 
	
	

}
