package com.alnt.platform.base.domain.dto;

import java.util.Date;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter 
public class BaseDTO extends DTO{

    /**
	 * 
	 */
	protected static final long serialVersionUID = -8642329718766344150L;
	
	//@JsonProperty(access = Access.READ_ONLY)
	@JsonView(JsonViews.Header.class)
	protected Integer intStatus;

	@JsonView(JsonViews.Header.class)
	protected Long createdBy;

	@JsonView(JsonViews.Header.class)
	protected Date createdOn;

	@JsonView(JsonViews.Header.class)
	protected Long changedBy;

	@JsonView(JsonViews.Header.class)
	protected Date changedOn;
	
	protected boolean doNotReplaceList = false;
	
	
	public boolean isActive() {
		return intStatus == null || intStatus == 0;
	}
	
	public boolean isDoNotReplaceList() {
		return doNotReplaceList;
	}

	public void setDoNotReplaceList(boolean doNotReplaceList) {
		this.doNotReplaceList = doNotReplaceList;
	}

	public Integer getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public Long getChangedBy() {
		return changedBy;
	}

	public Date getChangedOn() {
		return changedOn;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public void setChangedBy(Long changedBy) {
		this.changedBy = changedBy;
	}

	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
	}
	
	

	
}
