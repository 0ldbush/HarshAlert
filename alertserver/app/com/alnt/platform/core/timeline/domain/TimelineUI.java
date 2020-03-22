package com.alnt.platform.core.timeline.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TIMELINE_UI")
public class TimelineUI extends com.alnt.platform.base.domain.Entity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "EVENTTYPE")
	private String eventType;

	@Column(name = "EVENTTYPETEXT")
	private String eventText;

	@Column(name = "BUSOBJCAT")
	private String busObjCat;

	@Column(name = "BUSOBJID")
	Long busObjID;

	@Column(name = "FIELD")
	String field;
	

	@Column(name = "FIELDNAME")
	String fieldName;

	@Column(name = "FIELDTOOLTIP")
	String fieldToolTip;
	
	@Column(name = "OLDVALUE")
	String oldValue;
	
	@Column(name = "NEWVALUE")
	String newValue;

	@Column(name = "USER_ID")
	Long user;
	
	@Column(name = "USERNAME")
	String userName;
	
	@Column(name = "CREATEDON")
	Date createdOn;
	
	@Column(name = "INT_STATUS")
	protected Integer intStatus = Integer.valueOf(0);
	

	public Integer getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
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

	public Long getBusObjID() {
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

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public void setBusObjID(Long busObjID) {
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
	
	
	

}
