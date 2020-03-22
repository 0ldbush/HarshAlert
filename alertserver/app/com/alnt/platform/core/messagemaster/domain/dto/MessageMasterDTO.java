package com.alnt.platform.core.messagemaster.domain.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;

public class MessageMasterDTO extends BaseMasterDTO{
	
	private static final long serialVersionUID = 1L;
	
	private String channel;

	private String subject;

	private String body;

	private String priority;

	private Set<String> fields = new HashSet<String>();

	private boolean adminMessage;

	protected List<MessageMasterButton> buttons = new ArrayList<>();

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public Set<String> getFields() {
		return fields;
	}

	public void setFields(Set<String> fields) {
		this.fields = fields;
	}

	public boolean isAdminMessage() {
		return adminMessage;
	}

	public void setAdminMessage(boolean adminMessage) {
		this.adminMessage = adminMessage;
	}

	public List<MessageMasterButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<MessageMasterButton> buttons) {
		this.buttons = buttons;
	}
	
	


}
