package com.alnt.platform.base.domain.dto;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

public abstract class BaseSocialDTO extends BaseDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonView(JsonViews.Header.class)
	private String extId;

	@JsonView(JsonViews.Header.class)
	private String text;
	
	@JsonView(JsonViews.Header.class)
	private String type;
	
	@JsonView(JsonViews.Header.class)
	private String busObjCat;

	@JsonView(JsonViews.Header.class)
	private Long busObjId;

	public String getExtId() {
		return extId;
	}

	public String getText() {
		return text;
	}

	public void setExtId(String extId) {
		this.extId = extId;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public Long getBusObjId() {
		return busObjId;
	}

	public void setBusObjId(Long busObjId) {
		this.busObjId = busObjId;
	}


}
