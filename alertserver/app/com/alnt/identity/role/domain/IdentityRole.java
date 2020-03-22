package com.alnt.identity.role.domain;

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
@Table(name = "AFBC_CUSTOM_ENTITY")
@Immutable
public class IdentityRole extends com.alnt.platform.base.domain.Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	protected Long id;

	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


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

	public Long getEntType() {
		return entType;
	}

	public void setEntType(Long entType) {
		this.entType = entType;
	}

	public String getLongdescription() {
		return longdescription;
	}

	public void setLongdescription(String longdescription) {
		this.longdescription = longdescription;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}



	public Long getProvisioningActive() {
		return provisioningActive;
	}

	public void setProvisioningActive(Long provisioningActive) {
		this.provisioningActive = provisioningActive;
	}

	public Long getDeleted() {
		return deleted;
	}

	public void setDeleted(Long deleted) {
		this.deleted = deleted;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;


	@Column(name = "ENTITY_NAME", nullable = false)
	private String extId;

	@Column(name = "ENTITY_DESC")
	private String description;

	@Column(name = "ENTITY_TYPEID", nullable = false)
	private Long entType;

	@Column(name = "ENTITY_LONG_DESC")
	private String longdescription;

	@Column(name = "ENTITY_STATUS")
	private Long status;

	@Column(name = "ENTITY_ALIAS")
	private String alias;

	@Column(name = "PROV_ACTIVE")
	private Long provisioningActive;

	@Column(name = "ENTITY_DELETED")
	private Long deleted;

	@Column(name = "ROLETYPE")
	private String roleType;


	

	@Column(name = "ENTITY_DISPLAY_NAME")
	private String text;

	


}
