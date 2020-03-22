package com.alnt.workflow.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;

public class WorkflowConditionDTO extends DTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;
	
	private String fieldName;
    
    private String operator;
    
    private String value;
    
    private Boolean isOr;
    
    protected Long workflowConnectionId;
    
    private String conditionApi;  

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getIsOr() {
		return isOr;
	}

	public void setIsOr(Boolean isOr) {
		this.isOr = isOr;
	}

	public Long getWorkflowConnectionId() {
		return workflowConnectionId;
	}

	public void setWorkflowConnectionId(Long workflowConnectionId) {
		this.workflowConnectionId = workflowConnectionId;
	}


	public String getConditionApi() {
		return conditionApi;
	}


	public void setConditionApi(String conditionApi) {
		this.conditionApi = conditionApi;
	}
    
    
}
