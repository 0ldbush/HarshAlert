package com.alnt.platform.base.domain;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;



@MappedSuperclass
public abstract class BaseEntity extends Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@Version
//    private Integer version;
	
	
	@Column(name = "INT_STATUS")
	protected Integer intStatus = Integer.valueOf(0);

	@Column(name = "CREATED_BY", length = 50)
	protected Long createdBy;
	
	@Column(name = "CREATED_ON")
	protected Date createdOn;
	
	@Column(name = "CHANGED_BY", length = 50)
	protected Long changedBy;
	
	@Column(name = "CHANGED_ON")
	protected Date changedOn;
	
	@Transient
	protected boolean doNotReplaceList = true;
	
	
	public Integer getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

	public boolean isDeleted() {
		return intStatus != null && intStatus == 3;
	}
	
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(Long changedBy) {
		this.changedBy = changedBy;
	}

	public Date getChangedOn() {
		return changedOn;
	}

	public void setChangedOn(Date changedOn) {
		this.changedOn = changedOn;
	}

//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}

	public boolean isDoNotReplaceList() {
		return doNotReplaceList;
	}

	public void setDoNotReplaceList(boolean doNotReplaceList) {
		this.doNotReplaceList = doNotReplaceList;
	}

	@PreUpdate
	public void updateAudit() {
		changedOn = Date.from(Instant.now());
		if(intStatus==null)
			intStatus=Integer.valueOf(0);
	}
	
	@PrePersist
	public void createAudit() {
		createdOn = Date.from(Instant.now());
		changedOn = Date.from(Instant.now());
		if(intStatus==null)
			intStatus=Integer.valueOf(0);
	}

	public enum INT_STATUS {
		ACTIVE(0), DRAFT(1), TEMPLATE(2), DELETED(3), ARCHIVE(4), INACTIVE(9);

		private int value;
		private static Map map = new HashMap<>();

		private INT_STATUS(int value) {
			this.value = value;
		}

		static {
			for (INT_STATUS intStatus : INT_STATUS.values()) {
				map.put(intStatus.value, intStatus);
			}
		}

		public static INT_STATUS valueOf(int intSTatus) {
			return (INT_STATUS) map.get(intSTatus);
		}

		public int getValue() {
			return value;
		}

	}
	
}
