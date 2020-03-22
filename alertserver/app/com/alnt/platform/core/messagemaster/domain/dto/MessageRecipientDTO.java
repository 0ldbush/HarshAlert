package com.alnt.platform.core.messagemaster.domain.dto;


import java.util.Date;

import com.alnt.platform.base.domain.dto.DTO;


// Message recipient

public class MessageRecipientDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int seq;

	private String userId;

	private Date readOn;

	private Date respondedOn;

	private int scoreResponse;
	
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