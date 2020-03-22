package com.alnt.workflow.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alnt.platform.base.domain.dto.BaseDTO;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

public class WorkflowDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

	private String workflowCode;
    
    private String text;
	
    private String busObjCat;
	
    private Long busObjId;
    
    private Set<String> busObjTypes = new HashSet<String>();
    
    private Long ownerId;
    
    private Boolean template;
    
    private Boolean noConnections;
    
    protected List<WorkflowRecipientDTO> recipients = new ArrayList<>();
    
    protected List<WorkflowStepDTO> steps = new ArrayList<>();
    
    protected List<WorkflowConnectionDTO> connections = new ArrayList<>();
    
    private String subItemStart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<WorkflowRecipientDTO> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<WorkflowRecipientDTO> recipients) {
		this.recipients = recipients;
	}

	public List<WorkflowStepDTO> getSteps() {
		return steps;
	}

	public void setSteps(List<WorkflowStepDTO> steps) {
		this.steps = steps;
	}

	public List<WorkflowConnectionDTO> getConnections() {
		return connections;
	}

	public void setConnections(List<WorkflowConnectionDTO> connections) {
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
	
	
}
