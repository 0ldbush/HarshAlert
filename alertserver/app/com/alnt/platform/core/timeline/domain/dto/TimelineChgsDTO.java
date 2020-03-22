package com.alnt.platform.core.timeline.domain.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: Timeline
 *
 */
public class TimelineChgsDTO {

	
	public TimelineChgsDTO() {
		super();
	}
    private Long id;
	

	private String  field;	//:String(100);	// 	Field changed including internal ID or index when changing a sub-document field (e.g. PurchaseOrder.items[3].amt)	String			
	
    private String  subType;	//:String(24);	//	 	Type of field level change (e.g. overwrite value, edit list, change status, add/remove files, edit comment etc.)	String		Lists.listEntries.entryID?TimelineSubType	
	
    private String  oldValue;	//:String(100);	//	 	Old, prior or removed values from a list	String			Y
	
    private String  newValue;	//:String(100);	//	 	New, updated or values added to a list	String			Y
	
    private String  subDocKey;	//:String(100);	//	Sub-document key fields which was changed	String	
	
    private Long  timelineId;	// : Association[1] to Timeline on timeline.id = timelineId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
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

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getSubDocKey() {
		return subDocKey;
	}

	public void setSubDocKey(String subDocKey) {
		this.subDocKey = subDocKey;
	}

	public Long getTimelineId() {
		return timelineId;
	}

	public void setTimelineId(Long timelineId) {
		this.timelineId = timelineId;
	}
	
    
	
}
