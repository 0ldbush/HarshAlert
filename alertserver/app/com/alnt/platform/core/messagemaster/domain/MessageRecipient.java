package com.alnt.platform.core.messagemaster.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


// Message recipient

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MESSAGE_RECIPIENT")
public class MessageRecipient extends com.alnt.platform.base.domain.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="SEQ")
	private int seq;

	@Column(name="USER_ID")
	private String userId;

	@Column(name="READ_ON")
	private Date readOn;

	@Column(name="RESPONDED_ON")
	private Date respondedOn;

	@Column(name="SCORE_RESPONSE")
	private int scoreResponse;
	
	@Column(name = "MESSAGE_LOG_ID")
	protected Long messageLogId;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getReadOn() {
		return readOn;
	}

	public void setReadOn(Date readOn) {
		this.readOn = readOn;
	}

	public Date getRespondedOn() {
		return respondedOn;
	}

	public void setRespondedOn(Date respondedOn) {
		this.respondedOn = respondedOn;
	}

	public int getScoreResponse() {
		return scoreResponse;
	}

	public void setScoreResponse(int scoreResponse) {
		this.scoreResponse = scoreResponse;
	}

	public Long getMessageLogId() {
		return messageLogId;
	}

	public void setMessageLogId(Long messageLogId) {
		this.messageLogId = messageLogId;
	}
	
	
}