package com.alnt.workflow.domain.dto;

import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.workflow.domain.WorkflowStep;

public class WorkflowApiInput {

	private Object busObj = null;
	private UserDTO user = null;
	private WorkflowStep step = null;
	private String userRole = null;
	protected Long subID = null;
	protected String multiSubProcessField = null;
	private InputStatusBean input = null;
	
	
	public Object getBusObj() {
		return busObj;
	}
	public void setBusObj(Object busObj) {
		this.busObj = busObj;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public WorkflowStep getStep() {
		return step;
	}
	public void setStep(WorkflowStep step) {
		this.step = step;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public Long getSubID() {
		return subID;
	}
	public void setSubID(Long subID) {
		this.subID = subID;
	}
	public String getMultiSubProcessField() {
		return multiSubProcessField;
	}
	public void setMultiSubProcessField(String multiSubProcessField) {
		this.multiSubProcessField = multiSubProcessField;
	}
	public InputStatusBean getInput() {
		return input;
	}
	public void setInput(InputStatusBean input) {
		this.input = input;
	}
	
	
}
