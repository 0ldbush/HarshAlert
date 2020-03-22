package com.alnt.platform.core.messagemaster.domain.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alnt.platform.base.domain.dto.BaseSocialDTO;

// Message or comment
public class MessageLogDTO extends BaseSocialDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String msgMasterId;

	private String subject;

	private String body;

	private String priority;

	private Boolean form;

	private Map<String, String> fieldValues = new HashMap<String, String>();

	private List<MessageButtonDTO> buttons = new ArrayList<MessageButtonDTO>();

	private Boolean adminMsg;

	private String conversationId;

	private List<MessageRecipientDTO> recipients = new ArrayList<MessageRecipientDTO>();

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

	public Map<String, String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(Map<String, String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public List<MessageButtonDTO> getButtons() {
		return buttons;
	}

	public void setButtons(List<MessageButtonDTO> buttons) {
		this.buttons = buttons;
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

	public List<MessageRecipientDTO> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<MessageRecipientDTO> recipients) {
		this.recipients = recipients;
	}

}