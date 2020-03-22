package com.alnt.workflow.domain.dto;

import com.alnt.access.user.domain.dto.UserDTO;

public class InputStatusBean {

	protected String busObjCat = null;
	protected Long busObjID = null;
	protected String busObjType = null;
	protected Long subID = null;
	protected String multiSubProcessField = null;
	protected String statusID = null;
	protected Long workflowID;
	protected UserDTO user = null;
	protected String customerID = null;
	protected String languageID = null;
	
	private String remark;
	
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBusObjCat() {
		return busObjCat;
	}
	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}
	public Long getBusObjID() {
		return busObjID;
	}
	public void setBusObjID(Long busObjID) {
		this.busObjID = busObjID;
	}
	public String getBusObjType() {
		return busObjType;
	}
	public void setBusObjType(String busObjType) {
		this.busObjType = busObjType;
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
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public Long getWorkflowID() {
		return workflowID;
	}
	public void setWorkflowID(Long workflowID) {
		this.workflowID = workflowID;
	}
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getLanguageID() {
		return languageID;
	}
	public void setLanguageID(String languageID) {
		this.languageID = languageID;
	}
	
	
}
