package com.alnt.identity.role.domain.dto;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;

public class IdentityRoleDTO extends BaseMasterDTO {
	


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



	private String description;

	private Long entType;

	private String longdescription;

	private Long status;

	private String alias;


	private Long provisioningActive;

	private Long deleted;

	private String roleType;




	

}
