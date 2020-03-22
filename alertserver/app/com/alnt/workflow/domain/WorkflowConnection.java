package com.alnt.workflow.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alnt.platform.base.persistence.CacheConstants;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="WORKFLOW_CONNECTION")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_WORKFLOW)
public class WorkflowConnection extends com.alnt.platform.base.domain.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
    
    @Column(name = "SOURCE")
    protected String source;
    
    @Column(name = "TARGET")
    protected String target;
    
    @Column(name="RESPONSE")
	private String response;
    
    @Column(name="PREFERRED")
	private Boolean preferred;
    
    @ManyToOne(targetEntity = Workflow.class, optional = false)
    @JoinColumn(name = "WORKFLOW_ID")
    protected Workflow workflow;
    
    @OneToMany(mappedBy = "workflowConnection", targetEntity = WorkflowCondition.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
	protected List<WorkflowCondition> constraints = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Boolean getPreferred() {
		return preferred != null ?preferred : Boolean.FALSE;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public List<WorkflowCondition> getConstraints() {
		return constraints;
	}

	public void setConstraints(List<WorkflowCondition> constraints) {
		this.constraints = constraints;
	}
	
	
}
