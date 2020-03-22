package com.alnt.mdm.ruleattr.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RULE_ATTR")
@SQLDelete(sql = "UPDATE RULE_ATTR SET INT_STATUS = 3 WHERE ID = ?")
@Getter @Setter 
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
    
}

