package com.alnt.identity.usergroup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "AFBC_USER_GROUP")
@Immutable
public class IdentityUserGroup extends com.alnt.platform.base.domain.Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	protected Long id;

	
	private static final long serialVersionUID = 1L;

	


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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}


	@Column(name = "user_group_name")
	private String extId;

	@Column(name = "short_desc")
	private String text;

	@Column(name = "description")
	private String description;


}
