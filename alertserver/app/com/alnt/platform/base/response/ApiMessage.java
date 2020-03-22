package com.alnt.platform.base.response;

import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class ApiMessage {
	
	private String messageText;
	
	private ApiMessageType messageType;
	
	private String messageCode;
	
	private String messageDisplayText;
	
	private String messageCategory;
	
	private int priority;
	
	private Map<String,String> valuesMap;
	
	public String getMessageText() {
		return messageText;
	}
	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
	public ApiMessageType getMessageType() {
		return messageType;
	}
	public void setMessageType(ApiMessageType messageType) {
		this.messageType = messageType;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}	
	public String getMessageDisplayText() {
		//String translatedStr = DynamicTranslationProvider.getValue(this, "messageDisplayText", messageDisplayText);
		//return getResolvedString(translatedStr, this.valuesMap);
		return getResolvedString(this.messageDisplayText, this.valuesMap);
	}
	public void setMessageDisplayText(String messageDisplayText) {
		this.messageDisplayText = messageDisplayText;
	}	
	public String getMessageCategory() {
		return messageCategory;
	}
	public void setMessageCategory(String messageCategory) {
		this.messageCategory = messageCategory;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Map<String,String> getValuesMap() {
		return valuesMap;
	}
	public void setValuesMap(Map<String,String> valuesMap) {
		this.valuesMap = valuesMap;
	}

	public ApiMessage(String messageText, ApiMessageType messageType) {
		super();
		this.messageText = messageText;
		this.messageType = messageType;
	}
	public ApiMessage(String messageText, ApiMessageType messageType, String messageCode) {
		super();
		this.messageText = messageText;
		this.messageType = messageType;
		this.messageCode = messageCode;
	}
	public ApiMessage(String messageText, ApiMessageType messageType, String messageCode, String messageDisplayText) {
		super();
		this.messageText = messageText;
		this.messageType = messageType;
		this.messageCode = messageCode;
		this.messageDisplayText = messageDisplayText;
	}
	
	public ApiMessage() {
		super();
	}
	
	private String getResolvedString(String template, Map<String,String> valuesMap) {
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		String resolvedString = sub.replace(template);		
		return resolvedString;
	}
	
}


