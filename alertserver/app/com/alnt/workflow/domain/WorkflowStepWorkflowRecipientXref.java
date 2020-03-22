package com.alnt.workflow.domain;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * The Class CategoryProductXrefImpl is the default implmentation of {@link Category}.
 * This entity is only used for executing a named query.
 *
 * If you want to add fields specific to your implementation of BroadLeafCommerce you should extend
 * this class and add your fields.  If you need to make significant changes to the class then you
 * should implement your own version of {@link Category}.
 * <br>
 * <br>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "WORKFLOW_STEP_WORKFLOW_RECIPIENT_XREF")
public class WorkflowStepWorkflowRecipientXref extends com.alnt.platform.base.domain.Entity{
	 

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "WorkflowStepWorkflowRecipientXrefId")
    @GenericGenerator(
        name="WorkflowStepWorkflowRecipientXrefId",
        strategy="com.minidukan.common.persistence.IdOverrideTableGenerator",
        parameters = {
            @Parameter(name="segment_value", value="WorkflowStepWorkflowRecipientXref"),
            @Parameter(name="entity_name", value="com.minidukan.core.workflow.domain.WorkflowStepWorkflowRecipientXref")
        }
    )
    @Column(name = "WORKFLOW_STEP_WORKFLOW_RECIPIENT_ID")
    protected Long id;

    @ManyToOne(targetEntity = WorkflowStep.class, optional=false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "WORKFLOW_STEP_ID")
    protected WorkflowStep workflowStep;

    /** The product. */
    @ManyToOne(targetEntity = WorkflowRecipient.class, optional=false, cascade = CascadeType.REFRESH)
    @JoinColumn(name = "WORKFLOW_STEP_RECIPIENT_ID")
    protected WorkflowRecipient workflowRecipient;

    /** The display order. */
    @Column(name = "SEQ")
    protected int seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public WorkflowStep getWorkflowStep() {
		return workflowStep;
	}
	
	public Long getWorkflowStepId() {
		return workflowStep != null ? workflowStep.getId() : null;
	}

	public void setWorkflowStep(WorkflowStep workflowStep) {
		this.workflowStep = workflowStep;
	}

	public WorkflowRecipient getWorkflowRecipient() {
		return workflowRecipient;
	}

	public void setWorkflowRecipient(WorkflowRecipient workflowRecipient) {
		this.workflowRecipient = workflowRecipient;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
	
}
