package com.alnt.platform.base.domain;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseSocialEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "EXT_ID", length = 50)
	private String extId;

	@Column(name = "TEXT")
	private String text;
	
	@Column(name = "TYPE")
	private String type;
	
	@Column(name="BUS_OBJ_CAT")
	private String busObjCat;

	@Column(name="BUS_OBJ_ID")
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
