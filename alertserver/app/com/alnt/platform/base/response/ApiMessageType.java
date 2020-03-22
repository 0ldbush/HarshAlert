package com.alnt.platform.base.response;

public enum ApiMessageType {
	ERROR("ERROR"), WARNING("WARNING"), INFO("INFO");
	private String messageType;

	private ApiMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String toString() {
		return messageType;
	}
}
