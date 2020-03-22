package com.alnt.workflow.domain.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class WorkflowStatusDTO {
	private State primaryState = new State();
	private List<WorkflowStatusDTO.State> otherStates = new ArrayList<WorkflowStatusDTO.State>();
	private List<WorkflowStatusDTO.State> nextStates = new ArrayList<WorkflowStatusDTO.State>();
	private Long workflowID = null;
	
	
	

	public Long getWorkflowID() {
		return workflowID;
	}

	public void setWorkflowID(Long workflowID) {
		this.workflowID = workflowID;
	}

	public State getPrimaryState() {
		return primaryState;
	}

	public void setPrimaryState(State primaryState) {
		this.primaryState = primaryState;
	}

	public List<WorkflowStatusDTO.State> getOtherState() {
		return otherStates;
	}

	public void setOtherState(List<WorkflowStatusDTO.State> otherStates) {
		this.otherStates = otherStates;
	}

	public List<WorkflowStatusDTO.State> getNextState() {
		return nextStates;
	}

	public void setNextState(List<WorkflowStatusDTO.State> nextStates) {
		this.nextStates = nextStates;
	}

	public class State {
		

		private String statusID;
		private List<String> statusIDs = new ArrayList<String>();
		@JsonIgnore
		private List<Long> workflowStepIDs = new ArrayList<Long>();
		private String type;
		private String response;
		private Boolean commentRequired;
		private boolean preferred;
		private Integer sequence;
		private Set<String> recipientUsers = new HashSet<String>();
		private String text;
		private String milestoneDescription;
		private String icon;
		private String tooltip;
		private Date setOn;
		private Long setByUser;
		private Long setByCustomer;
		private String setByUserFullName;
		private String setByCustomerFullName;
		private Date dueOn;
		private Boolean completed = Boolean.FALSE;
		private Boolean current = Boolean.FALSE;
		private Map<String, String> attributes = new HashMap<String, String>();

		@JsonIgnore
		private String status;
		
		public Map<String, String> getAttributes() {
			return attributes;
		}

		public void setAttributes(Map<String, String> attributes) {
			this.attributes = attributes;
		}

		public String getMilestoneDescription() {
			return milestoneDescription;
		}

		public void setMilestoneDescription(String milestoneDescription) {
			this.milestoneDescription = milestoneDescription;
		}
		public Boolean getCommentRequired() {
			return commentRequired;
		}
		public void setCommentRequired(Boolean commentRequired) {
			this.commentRequired = commentRequired;
		}
		public String getStatusID() {
			return statusID;
		}
		public void setStatusID(String statusID) {
			this.statusID = statusID;
		}
		public List<String> getStatusIDs() {
			return statusIDs;
		}
		public void setStatusIDs(List<String> statusIDs) {
			this.statusIDs = statusIDs;
		}
		public String isType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getResponse() {
			return response;
		}
		public void setResponse(String response) {
			this.response = response;
		}
		public boolean isPreferred() {
			return preferred;
		}
		public void setPreferred(boolean preferred) {
			this.preferred = preferred;
		}
		public Integer getSequence() {
			return sequence;
		}
		public void setSequence(Integer sequence) {
			this.sequence = sequence;
		}
		public Set<String> getRecipientUsers() {
			return recipientUsers;
		}
		public void setRecipientUsers(Set<String> recipientUsers) {
			this.recipientUsers = recipientUsers;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getTooltip() {
			return tooltip;
		}
		public void setTooltip(String tooltip) {
			this.tooltip = tooltip;
		}
		public Date getSetOn() {
			return setOn;
		}
		public void setSetOn(Date setOn) {
			this.setOn = setOn;
		}
		public Long getSetByUser() {
			return setByUser;
		}
		public void setSetByUser(Long setByUser) {
			this.setByUser = setByUser;
		}
		public Long getSetByCustomer() {
			return setByCustomer;
		}
		public void setSetByCustomer(Long setByCustomer) {
			this.setByCustomer = setByCustomer;
		}
		public String getSetByUserFullName() {
			return setByUserFullName;
		}
		public void setSetByUserFullName(String setByUserFullName) {
			this.setByUserFullName = setByUserFullName;
		}
		public String getSetByCustomerFullName() {
			return setByCustomerFullName;
		}
		public void setSetByCustomerFullName(String setByCustomerFullName) {
			this.setByCustomerFullName = setByCustomerFullName;
		}
		public Date getDueOn() {
			return dueOn;
		}
		public void setDueOn(Date dueOn) {
			this.dueOn = dueOn;
		}
		public Boolean getCompleted() {
			return completed;
		}
		public void setCompleted(Boolean completed) {
			this.completed = completed;
		}
		public Boolean getCurrent() {
			return current;
		}
		public void setCurrent(Boolean current) {
			this.current = current;
		}

		public List<Long> getWorkflowStepIDs() {
			return workflowStepIDs;
		}

		public void setWorkflowStepIDs(List<Long> workflowStepIDs) {
			this.workflowStepIDs = workflowStepIDs;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		
		
	}
	
}
