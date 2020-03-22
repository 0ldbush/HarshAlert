package com.alnt.platform.core.classdef.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;


public class FieldDefDTO extends DTO{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String classID;
	
	private String fieldname;

	private String label;

	private String tooltip;
    
	private String type;
    
	private boolean isPrimitive;
    
	private String xType;

	private String referenceKey;
    
	private boolean isList;
    
	private boolean isTranslated;
    
	private boolean isRequired;
    
	private boolean isBaseClassField;
    
	private boolean isUnique;
    
	private boolean noHistory;

	private boolean doNotCopy;
    
	private boolean isDimension;

	private boolean isMeasure;
	
	private String uri;


	public boolean getIsPrimitive() {
		return isPrimitive;
	}

	public void setIsPrimitive(boolean isPrimitive) {
		this.isPrimitive = isPrimitive;
	}

	public String getClassID() {
		return classID;
	}

	public String getFieldname() {
		return fieldname;
	}

	public String getLabel() {
		return label;
	}

	public String getTooltip() {
		return tooltip;
	}

	public String getType() {
		return type;
	}

	public String getxType() {
		return xType;
	}

	public String getReferenceKey() {
		return referenceKey;
	}

	public boolean getIsList() {
		return isList;
	}

	public boolean getIsTranslated() {
		return isTranslated;
	}

	public boolean getIsRequired() {
		return isRequired;
	}

	public boolean getIsBaseClassField() {
		return isBaseClassField;
	}

	public boolean getIsUnique() {
		return isUnique;
	}

	public boolean getNoHistory() {
		return noHistory;
	}

	public boolean getDoNotCopy() {
		return doNotCopy;
	}


	public void setClassID(String classID) {
		this.classID = classID;
	}

	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setxType(String xType) {
		this.xType = xType;
	}

	public void setReferenceKey(String referenceKey) {
		this.referenceKey = referenceKey;
	}

	public void setIsList(boolean isList) {
		this.isList = isList;
	}

	public void setIsTranslated(boolean isTranslated) {
		this.isTranslated = isTranslated;
	}

	public void setIsRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public void setIsBaseClassField(boolean isBaseClassField) {
		this.isBaseClassField = isBaseClassField;
	}

	public void setIsUnique(boolean isUnique) {
		this.isUnique = isUnique;
	}

	public void setNoHistory(boolean noHistory) {
		this.noHistory = noHistory;
	}

	public void setDoNotCopy(boolean doNotCopy) {
		this.doNotCopy = doNotCopy;
	}

	public boolean getIsDimension() {
		return isDimension;
	}

	public void setIsDimension(boolean isDimension) {
		this.isDimension = isDimension;
	}

	public boolean getIsMeasure() {
		return isMeasure;
	}

	public void setIsMeasure(boolean isMeasure) {
		this.isMeasure = isMeasure;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
