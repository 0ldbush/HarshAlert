package com.alnt.platform.base.domain.dto;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

public abstract class BaseMasterDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Header.class)
	private String extId;

	@JsonView(JsonViews.Header.class)
	private String text;

	@JsonView(JsonViews.Header.class)
	private String description;
	
	@JsonView(JsonViews.Header.class)
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
