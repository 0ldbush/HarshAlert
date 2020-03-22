package com.alnt.platform.core.timeline.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entity implementation class for Entity: Timeline
 *
 */
@Entity
@Table(name = "TIMELINE")
public class Timeline extends com.alnt.platform.base.domain.Entity{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public Timeline() {
		super();
	}
	
    @Column(name="busObjCat")
	private String  busObjCat;	//:association[1] to BusObjCat{extID}; 	//Category or type of document changed	String		BusObjCat.extID
    
    @Column(name="busObjID")
    private Long busObjID;	//:String(48);	//	Document	ID of document changed	ObjectId	

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="timelineId", referencedColumnName="id", foreignKey = @ForeignKey)
	private List<TimelineChgs> chgs   = new ArrayList<TimelineChgs>();	//:Association[*] to TimelineChgs on chgs.timelineId = id; //	Changes	Details of changes made	TimelineChgs	
    
    @Column(name="ipAddress")
    private String	ipAddress;	//:String(15);			//IP address	IP address of computer triggering the change	String		

    @Column(name="timelineType")
    private String	timelineType;	//:String(15);			//IP address	IP address of computer triggering the change	String		

    
    @Column(name="sessionID")
    private String sessionID;	//:String(34);	//User's session	Session identifier of user triggering the change	ObjectId		Session.id
    
    @Column(name="isMobile")
    private boolean	isMobile = false;	//Change type	Type of change (e.g. update, create, delete, add/remove file, add/edit comment)	String		List.listEntries.entryID?TimelineType
	
    @Column(name="createdOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn = Date.from(Instant.now());	//:UTCTimestamp;	//	Changed on	Date/time of change	date		
	
    @Column(name="createdBy")
    private Long	createdBy;	//:association to userresource.User{id};	//Changed by	User who posted the change	ObjectId		User.id

    @Column(name="reason", length = 500)
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

	public List<TimelineChgs> getChgs() {
		return chgs;
	}

	public void setChgs(List<TimelineChgs> chgs) {
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
