package com.alnt.workflow.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.persistence.CacheConstants;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="WORKFLOW")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_WORKFLOW)
@SQLDelete(sql="UPDATE WORKFLOW SET INT_STATUS = 3 WHERE WORKFLOW_ID = ?")
public class Workflow extends BaseEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="WORKFLOW_CODE")
	private String workflowCode;
    
    @Column(name="TEXT")
	private String text;
	
    @Column(name="BUSINESS_OBJECT_CATEGORY")
	private String busObjCat;
	
    @Column(name="BUSINESS_OBJECT_ID")
	private Long busObjId;
    
    @ElementCollection
    @CollectionTable(name = "WORKFLOW_BUS_OBJ_TYPES", joinColumns = @JoinColumn(name = "WORKFLOW_ID"))
    @Column(name = "BUS_OBJ_TYPE")
    private Set<String> busObjTypes = new HashSet<String>();
    
    @Column(name="OWNER_ID")
	private Long ownerId;
    
    @Column(name="TEMPLATE")
	private Boolean template;
    
    @Column(name="NO_CONNECTIONS")
	private Boolean noConnections;
    
    
    @OneToMany(mappedBy = "workflow", targetEntity = WorkflowRecipient.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
    @OrderBy(value="seq")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
    @BatchSize(size = 50)
    protected List<WorkflowRecipient> recipients = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "workflow", targetEntity = WorkflowStep.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
    @OrderBy(value="seq")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
    @BatchSize(size = 50)
    protected List<WorkflowStep> steps = new ArrayList<>();
    
    
    @OneToMany(mappedBy = "workflow", targetEntity = WorkflowConnection.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
    @BatchSize(size = 50)
    protected List<WorkflowConnection> connections = new ArrayList<>();
    
    @Column(name="SUB_ITEM_START")
	private String subItemStart;
    
    @Column(name="DISPLAY_TEMPLATE")
	private Boolean displayTemplate;
    
    private transient String firstStepCode;

	public String getWorkflowCode() {
		return workflowCode;
	}

	public void setWorkflowCode(String workflowCode) {
		this.workflowCode = workflowCode;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public Set<String> getBusObjTypes() {
		return busObjTypes;
	}

	public void setBusObjTypes(Set<String> busObjTypes) {
		this.busObjTypes = busObjTypes;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Boolean getTemplate() {
		return template != null ? template : Boolean.FALSE;
	}

	public void setTemplate(Boolean template) {
		this.template = template;
	}

	public Boolean getNoConnections() {
		return noConnections != null ? noConnections : Boolean.FALSE;
	}

	public void setNoConnections(Boolean noConnections) {
		this.noConnections = noConnections;
	}

	public List<WorkflowRecipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<WorkflowRecipient> recipients) {
		this.recipients = recipients;
	}

	public List<WorkflowStep> getSteps() {
		return steps;
	}

	public void setSteps(List<WorkflowStep> steps) {
		this.steps = steps;
	}

	public List<WorkflowConnection> getConnections() {
		return connections;
	}

	public void setConnections(List<WorkflowConnection> connections) {
		this.connections = connections;
	}

	public String getSubItemStart() {
		return subItemStart;
	}

	public void setSubItemStart(String subItemStart) {
		this.subItemStart = subItemStart;
	}

	public Long getBusObjId() {
		return busObjId;
	}

	public void setBusObjId(Long busObjId) {
		this.busObjId = busObjId;
	}

	public Boolean getDisplayTemplate() {
		return displayTemplate;
	}

	public void setDisplayTemplate(Boolean displayTemplate) {
		this.displayTemplate = displayTemplate;
	}

	public String getFirstStepCode() {
		return firstStepCode;
	}

	public void setFirstStepCode(String firstStepCode) {
		this.firstStepCode = firstStepCode;
	}

	
	
}
