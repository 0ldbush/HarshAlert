package com.alnt.platform.core.timeline.domain.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alnt.platform.base.domain.dto.DTO;

/**
 * Entity implementation class for Entity: Timeline
 *
 */
public class TimelineDTO extends DTO{

	
	public TimelineDTO() {
		super();
	}
	
	private String  busObjCat;	//:association[1] to BusObjCat{extID}; 	//Category or type of document changed	String		BusObjCat.extID
    
    private Long busObjID;	//:String(48);	//	Document	ID of document changed	ObjectId	

	private List<TimelineChgsDTO> chgs   = new ArrayList<TimelineChgsDTO>();	//:Association[*] to TimelineChgs on chgs.timelineId = id; //	Changes	Details of changes made	TimelineChgs	
    
    private String	ipAddress;	//:String(15);			//IP address	IP address of computer triggering the change	String		

    private String	timelineType;	//:String(15);			//IP address	IP address of computer triggering the change	String		

    
    private String sessionID;	//:String(34);	//User's session	Session identifier of user triggering the change	ObjectId		Session.id
    
    private boolean	isMobile = false;	//Change type	Type of change (e.g. update, create, delete, add/remove file, add/edit comment)	String		List.listEntries.entryID?TimelineType
	
    private Date createdOn = Date.from(Instant.now());	//:UTCTimestamp;	//	Changed on	Date/time of change	date		
	
    private Long	createdBy;	//:association to userresource.User{id};	//Changed by	User who posted the change	ObjectId		User.id

    private String	reason;	//:association to userresource.User{id};	//Changed by	User who posted the change	ObjectId		User.id

    
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

	public List<TimelineChgsDTO> getChgs() {
		return chgs;
	}

	public void setChgs(List<TimelineChgsDTO> chgs) {
		this.chgs = chgs;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public boolean getIsMobile() {
		return isMobile;
	}

	public void setIsMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public String getTimelineType() {
		return timelineType;
	}

	public void setTimelineType(String timelineType) {
		this.timelineType = timelineType;
	}

	public void setMobile(boolean isMobile) {
		this.isMobile = isMobile;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	
   
	
    
}
