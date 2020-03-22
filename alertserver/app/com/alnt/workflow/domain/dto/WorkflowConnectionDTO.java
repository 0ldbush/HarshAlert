package com.alnt.workflow.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.DTO;

public class WorkflowConnectionDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;
    
    protected Long sourceId;
    
    protected Long targetId;
    
    private String response;
    
    private Boolean preferred;
    
    protected Long workflowId;
    
    protected List<WorkflowConditionDTO> constraints = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSourceId() {
		return sourceId;
	}

	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public List<WorkflowConditionDTO> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<WorkflowConditionDTO> constraints) {
		this.constraints = constraints;
	}
	
    
}
