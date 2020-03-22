package com.alnt.platform.core.classdef.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FIELD_DEF")
public class FieldDef extends com.alnt.platform.base.domain.Entity{
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="CLASS_EXT_ID")
	private String classID;
	
    @Column(name="FIELD_NAME")
	private String fieldname;

    @Column(name="LABEL")
	private String label;

    @Column(name="TOOLTIP")
	private String tooltip;
    
    @Column(name="TYPE")
	private String type;
    
    @Column(name="IS_PRIMITIVE")
	private boolean isPrimitive;
    
    
    @Column(name="XTYPE")
	private String xType;

    @Column(name="REFERENCE_KEY")
	private String referenceKey;
    
    
    @Column(name="IS_LIST")
	private boolean isList;
    
    @Column(name="IS_TRANSLATED")
	private boolean isTranslated;
    
    @Column(name="IS_REQUIRED")
	private boolean isRequired;
    
    @Column(name="IS_BASE_CLASS_FIELD")
	private boolean isBaseClassField;
    
    @Column(name="IS_UNIQUE")
	private boolean isUnique;
    
    @Column(name="NO_HISTORY")
	private boolean noHistory;

    @Column(name="DO_NOT_COPY")
	private boolean doNotCopy;
    
    @Column(name="IS_DIMENSION")
	private boolean isDimension;

    @Column(name="IS_MEASURE")
	private boolean isMeasure;
    
    @Column(name="URI")
	private String uri;

	

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

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
    

    
	
	
}
