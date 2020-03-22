package com.alnt.platform.base.domain.dto;

public abstract class BaseSettingDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String extId;

	private String text;

	private String description;
	
	private String docNumberRange;

	public String getExtId() {
		return extId;
	}

	public String getText() {
		return text;
	}

	public String getDescription() {
		return description;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDocNumberRange() {
		return docNumberRange;
	}

	public void setDocNumberRange(String docNumberRange) {
		this.docNumberRange = docNumberRange;
	}

	
}
