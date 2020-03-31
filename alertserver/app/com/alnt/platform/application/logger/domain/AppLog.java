package com.alnt.platform.application.logger.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseSocialEntity;


// Message or comment

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "APP_LOG")
@SQLDelete(sql = "UPDATE APP_LOG SET INT_STATUS = 3 WHERE ID = ?")

public class AppLog extends BaseSocialEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="MESSAGE_MASTER_ID")
	private String msgMasterId;
	
	@Column(name="SUBJECT")
	private String subject;

	//Message email body or long text	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name="BODY")
	private String body;

	@Column(name="PRIORITY")
	private String priority;

	@Column(name="FORM")
	private Boolean form;

	@Column(name="STAGE")
    private String stage;
	
	

	@Column(name="ADMIN_MESSAGE")
	private Boolean adminMsg;

	@Column(name="CONVERSATION_ID")
	private String conversationId;



	public String getMsgMasterId() {
		return msgMasterId;
	}

	public void setMsgMasterID(String msgMasterId) {
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

	public void setConversationID(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public void setMsgMasterId(String msgMasterId) {
		this.msgMasterId = msgMasterId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	
	
}