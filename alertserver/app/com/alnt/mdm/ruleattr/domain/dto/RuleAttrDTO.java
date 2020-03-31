package com.alnt.mdm.ruleattr.domain.dto;

import com.alnt.platform.base.domain.dto.BaseDTO;

public class RuleAttrDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String extId;

	private String text;

	private String description;
	
	private int priority;
    
	private String entity;
	
	private String dataType;

	public String getExtId() {
		return extId;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
    
}

