package com.alnt.mdm.ruleattr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RULE_ATTR")
@SQLDelete(sql = "UPDATE RULE_ATTR SET INT_STATUS = 3 WHERE ID = ?")
public class RuleAttr extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "EXT_ID", length = 50)
	private String extId;

	@Column(name = "TEXT")
	private String text;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PRIORITY")
	private int priority;
	
	@Column(name = "ENTITY")
	private String entity;
	
	
	@Column(name = "DATA_TYPE")
	private String dataType;


	public String getExtId() {
		return extId;
	}


	public void setExtId(String extId) {
		this.extId = extId;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getPriority() {
		return priority;
	}


	public void setPriority(int priority) {
		this.priority = priority;
	}


	public String getEntity() {
		return entity;
	}


	public void setEntity(String entity) {
		this.entity = entity;
	}


	public String getDataType() {
		return dataType;
	}


	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	
    
}

