package com.alnt.platform.application.logger.domain.dto;

import com.alnt.platform.base.domain.dto.BaseSocialDTO;

public class AppLogDTO extends BaseSocialDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msgMasterId;

	private String subject;

	private String body;

	private String priority;

	private Boolean form;

	private String stage;

	private Boolean adminMsg;

	private String conversationId;
	

	public AppLogDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public AppLogDTO(String subject, String body,Long busObjId, String stage) {
		super();
		this.subject = subject;
		this.body = body;
		this.stage = stage;
		this.setBusObjId(busObjId);
	}

	


	public AppLogDTO(String stage) {
		super();
		this.stage = stage;
	}



	public String getMsgMasterId() {
		return msgMasterId;
	}

	public void setMsgMasterId(String msgMasterId) {
		this.msgMasterId = msgMasterId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean getForm() {
		return form;
	}

	public void setForm(Boolean form) {
		this.form = form;
	}

	public Boolean getAdminMsg() {
		return adminMsg;
	}

	public void setAdminMsg(Boolean adminMsg) {
		this.adminMsg = adminMsg;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}


	
}