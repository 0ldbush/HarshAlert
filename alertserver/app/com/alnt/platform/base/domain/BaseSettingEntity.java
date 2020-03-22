package com.alnt.platform.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseSettingEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	// Code or ID
	@Column(name = "EXT_ID", length = 50)
	private String extId;

	// Name/Text
	@Column(name = "TEXT", length = 50)
	private String text;

	// Description
	@Column(name = "DESCRIPTION", length = 200)
	private String description;
	
	@Column(name = "DOC_NUMBER", length = 50)
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
