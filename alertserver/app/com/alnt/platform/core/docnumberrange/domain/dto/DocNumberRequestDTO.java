package com.alnt.platform.core.docnumberrange.domain.dto;

public class DocNumberRequestDTO {
	
	String busObjCat;
	
	String busObjTypeId;
	
	int fiscalYear;
	
	String userDocNum;
	
	String docNumberRangeId;

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

}
