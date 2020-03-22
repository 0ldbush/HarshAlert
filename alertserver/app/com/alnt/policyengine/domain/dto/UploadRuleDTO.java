package com.alnt.policyengine.domain.dto;

public class UploadRuleDTO {
	
private Long fileId;

private String entityId;

private String enforcement;

public Long getFileId() {
	return fileId;
}

public void setFileId(Long fileId) {
	this.fileId = fileId;
}

public String getEnforcement() {
	return enforcement;
}

public void setEnforcement(String enforcement) {
	this.enforcement = enforcement;
}

public String getEntityId() {
	return entityId;
}

public void setEntityId(String entityId) {
	this.entityId = entityId;
}




}
