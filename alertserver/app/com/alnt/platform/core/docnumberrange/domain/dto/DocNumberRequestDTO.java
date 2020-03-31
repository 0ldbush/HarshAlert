package com.alnt.platform.core.docnumberrange.domain.dto;

public class DocNumberRequestDTO {
	
	private String busObjCat;
	
	private String busObjTypeId;
	
	private int fiscalYear;
	
	private String userDocNum;
	
	private String docNumberRangeId;
	
	private String busObjCatClazz;

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public String getBusObjTypeId() {
		return busObjTypeId;
	}

	public void setBusObjTypeId(String busObjTypeId) {
		this.busObjTypeId = busObjTypeId;
	}

	public int getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(int fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getUserDocNum() {
		return userDocNum;
	}

	public void setUserDocNum(String userDocNum) {
		this.userDocNum = userDocNum;
	}

	public String getDocNumberRangeId() {
		return docNumberRangeId;
	}

	public void setDocNumberRangeId(String docNumberRangeId) {
		this.docNumberRangeId = docNumberRangeId;
	}

	public String getBusObjCatClazz() {
		return busObjCatClazz;
	}

	public void setBusObjCatClazz(String busObjCatClazz) {
		this.busObjCatClazz = busObjCatClazz;
	}

}
