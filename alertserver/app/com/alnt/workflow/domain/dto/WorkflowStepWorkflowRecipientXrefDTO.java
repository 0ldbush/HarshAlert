package com.alnt.workflow.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;

public class WorkflowStepWorkflowRecipientXrefDTO extends DTO{
	 

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

    protected Long workflowStepId ;

    protected WorkflowRecipientDTO workflowRecipient = new WorkflowRecipientDTO();

    protected int seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getWorkflowStepId() {
		return workflowStepId;
	}

	public void setWorkflowStepId(Long workflowStepId) {
		this.workflowStepId = workflowStepId;
	}

	public WorkflowRecipientDTO getWorkflowRecipient() {
		return workflowRecipient;
	}

	public void setWorkflowRecipient(WorkflowRecipientDTO workflowRecipient) {
		this.workflowRecipient = workflowRecipient;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
    
    
	
}
