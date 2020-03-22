package com.alnt.platform.core.timeline.domain.dto;

import java.util.Date;

import com.alnt.platform.base.domain.dto.DTO;

public class TimelineUIDTO extends DTO{

	 
	private String eventType;

	private String eventText;

	private String busObjCat;

	String busObjID;

	String field;
	
	String fieldName;

	String fieldToolTip;
	
	String oldValue;
	
	String newValue;

	Long user;
	
	String userName;
	
	Date createdOn;
	
	protected Integer intStatus;

	public Long getId() {
		return id;
	}

	public String getEventType() {
		return eventType;
	}

	public String getEventText() {
		return eventText;
	}

	public String getBusObjCat() {
		return busObjCat;
	}

	public String getBusObjID() {
		return busObjID;
	}

	public String getField() {
		return field;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldToolTip() {
		return fieldToolTip;
	}


	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public Long getUser() {
		return user;
	}

	public String getUserName() {
		return userName;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public void setBusObjID(String busObjID) {
		this.busObjID = busObjID;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public void setFieldToolTip(String fieldToolTip) {
		this.fieldToolTip = fieldToolTip;
	}

	
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public void setUser(Long user) {
		this.user = user;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Integer getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}
	

}
