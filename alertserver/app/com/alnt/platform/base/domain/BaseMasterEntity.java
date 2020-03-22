package com.alnt.platform.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseMasterEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "EXT_ID", length = 50)
	private String extId;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "TYPE")
	private String type;
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
