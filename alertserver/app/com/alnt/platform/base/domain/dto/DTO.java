package com.alnt.platform.base.domain.dto;

import java.io.Serializable;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	protected String dataLocaleCode = "en";
	
	protected Long id;
	
	protected String id_str;
	
	private String clientSequenceId;
	
	private String busObjCat;

	public String getDataLocaleCode() {
		return dataLocaleCode;
	}

	public void setDataLocaleCode(String dataLocaleCode) {
		this.dataLocaleCode = dataLocaleCode;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClientSequenceId() {
		return clientSequenceId;
	}

	public void setClientSequenceId(String clientSequenceId) {
		this.clientSequenceId = clientSequenceId;
	}

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public String getId_str() {
		return id_str;
	}

	public void setId_str(String id_str) {
		this.id_str = id_str;
	}

}
