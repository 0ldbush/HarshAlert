package com.alnt.workflow.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.alnt.platform.base.persistence.CacheConstants;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="WORKFLOW_CONDITION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_WORKFLOW)
public class WorkflowCondition extends com.alnt.platform.base.domain.Entity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="FIELD_NAME")
    private String fieldName;
    
    @Column(name="OPERATOR")
    private String operator;
    
    @Column(name="VALUE")
    private String value;
    
    
    @Column(name="IS_OR")
    private Boolean isOr;  

    @Column(name="CONDITION_API")
	private String conditionApi;  
    
    @ManyToOne(targetEntity = WorkflowConnection.class)
    @JoinColumn(name = "WORKFLOW_CONNECTION_ID")
    protected WorkflowConnection workflowConnection;


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
		return isOr != null ? isOr : Boolean.FALSE;
	}


	public void setIsOr(Boolean isOr) {
		this.isOr = isOr;
	}


	public WorkflowConnection getWorkflowConnection() {
		return workflowConnection;
	}


	public void setWorkflowConnection(WorkflowConnection workflowConnection) {
		this.workflowConnection = workflowConnection;
	}
	
	public Long getWorkflowConnectionId() {
		return workflowConnection != null ? workflowConnection.getId() : null;
	}


	public String getConditionApi() {
		return conditionApi;
	}


	public void setConditionApi(String conditionApi) {
		this.conditionApi = conditionApi;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conditionApi == null) ? 0 : conditionApi.hashCode());
		result = prime * result + ((fieldName == null) ? 0 : fieldName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isOr == null) ? 0 : isOr.hashCode());
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
		WorkflowCondition other = (WorkflowCondition) obj;
		if (conditionApi == null) {
			if (other.conditionApi != null)
				return false;
		} else if (!conditionApi.equals(other.conditionApi))
			return false;
		if (fieldName == null) {
			if (other.fieldName != null)
				return false;
		} else if (!fieldName.equals(other.fieldName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isOr == null) {
			if (other.isOr != null)
				return false;
		} else if (!isOr.equals(other.isOr))
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
	
	
	
}
