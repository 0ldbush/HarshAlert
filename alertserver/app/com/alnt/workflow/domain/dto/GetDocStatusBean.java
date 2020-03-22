package com.alnt.workflow.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetDocStatusBean extends InputStatusBean{

	private String statusID = null;
	private List<String> otherStates = new ArrayList<String>();
	private Date readDate = null;
	private List<String> serviceIDs = new ArrayList<String>();
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public List<String> getOtherStates() {
		return otherStates;
	}
	public void setOtherStates(List<String> otherStates) {
		this.otherStates = otherStates;
	}
	public Date getReadDate() {
		return readDate;
	}
	public void setReadDate(Date readDate) {
		this.readDate = readDate;
	}
	public List<String> getServiceIDs() {
		return serviceIDs;
	}
	public void setServiceIDs(List<String> serviceIDs) {
		this.serviceIDs = serviceIDs;
	}
	
	
	
}
