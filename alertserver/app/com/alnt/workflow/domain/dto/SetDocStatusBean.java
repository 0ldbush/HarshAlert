package com.alnt.workflow.domain.dto;

import java.util.Date;

public class SetDocStatusBean extends InputStatusBean{

	private String sourceRefID = null;
	private Integer client = null;
	private Date updateDate = null;
	private String nextStepExtID = null;
	private String messageLogID;
	private String[] recepientsOverride;
	private Integer systemStatus;
	private boolean calledFromCreateWorkflow = false;
	public String getSourceRefID() {
		return sourceRefID;
	}
	public void setSourceRefID(String sourceRefID) {
		this.sourceRefID = sourceRefID;
	}
	public Integer getClient() {
		return client;
	}
	public void setClient(Integer client) {
		this.client = client;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getNextStepExtID() {
		return nextStepExtID;
	}
	public void setNextStepExtID(String nextStepExtID) {
		this.nextStepExtID = nextStepExtID;
	}
	
	public String getMessageLogID() {
		return messageLogID;
	}
	public void setMessageLogID(String messageLogID) {
		this.messageLogID = messageLogID;
	}
	public String[] getRecepientsOverride() {
		return recepientsOverride;
	}
	public void setRecepientsOverride(String[] recepientsOverride) {
		this.recepientsOverride = recepientsOverride;
	}
	public Integer getSystemStatus() {
		return systemStatus;
	}
	public void setSystemStatus(Integer systemStatus) {
		this.systemStatus = systemStatus;
	}
	public boolean isCalledFromCreateWorkflow() {
		return calledFromCreateWorkflow;
	}
	public void setCalledFromCreateWorkflow(boolean calledFromCreateWorkflow) {
		this.calledFromCreateWorkflow = calledFromCreateWorkflow;
	}
	
	
	
}
