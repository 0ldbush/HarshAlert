package com.alnt.platform.core.classdef.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;

@Entity
@Table(name = "CLASS_DEF")
@SQLDelete(sql = "UPDATE CLASS_DEF SET INT_STATUS = 3 WHERE ID = ?")
public class ClassDef extends BaseMasterEntity{

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Column(name="BUS_OBJ_CAT")
    private String busObjCat;

    @Column(name="FIELD_IN_PARENT")
    private String fieldInParent;
    
    @Column(name="REF_FIELD")
    private String refField;
    
    @Column(name="CLASS_NAME")
    private String className;
    
    @Column(name="PARENT_BUS_OBJ_CAT")
    private String parentBusObjCat;

	@Column(name="IS_RULE_PARENT")
    private boolean isRuleParent;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="CLASS_EXT_ID", referencedColumnName="EXT_ID", foreignKey = @ForeignKey)
    private List<FieldDef> fieldDefs = new ArrayList<FieldDef>();
    
    public boolean getIsRuleParent() {
		return isRuleParent;
	}

	public void setIsRuleParent(boolean isRuleParent) {
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

	public List<FieldDef> getFieldDefs() {
		return fieldDefs;
	}

	public void setFieldDefs(List<FieldDef> fieldDefs) {
		this.fieldDefs = fieldDefs;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}   
	
	
	
}
