package com.alnt.platform.core.classdef.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;


public class ClassDefDTO extends BaseMasterDTO{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String busObjCat;

    private String fieldInParent;
    
    private String refField;
    
    private String parentBusObjCat;
    
	private Boolean isRuleParent;
	
    private Boolean docNumberSupported;
	
    private Boolean editable;
	
	private String className;
    
    private List<FieldDefDTO> fieldDefs = new ArrayList<FieldDefDTO>();
    
    
    public Boolean getIsRuleParent() {
		return isRuleParent;
	}

	public void setIsRuleParent(Boolean isRuleParent) {
		this.isRuleParent = isRuleParent;
	}

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public String getRefField() {
		return refField;
	}

	public void setRefField(String refField) {
		this.refField = refField;
	}

	public String getParentBusObjCat() {
		return parentBusObjCat;
	}

	public void setParentBusObjCat(String parentBusObjCat) {
		this.parentBusObjCat = parentBusObjCat;
	}

	public String getFieldInParent() {
		return fieldInParent;
	}

	public void setFieldInParent(String fieldInParent) {
		this.fieldInParent = fieldInParent;
	}

	public List<FieldDefDTO> getFieldDefs() {
		return fieldDefs;
	}

	public void setFieldDefs(List<FieldDefDTO> fieldDefs) {
		this.fieldDefs = fieldDefs;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Boolean getDocNumberSupported() {
		return docNumberSupported;
	}

	public void setDocNumberSupported(Boolean docNumberSupported) {
		this.docNumberSupported = docNumberSupported;
	}

	public Boolean getEditable() {
		return editable;
	}

	public void setEditable(Boolean editable) {
		this.editable = editable;
	}   
	
	
	
}
