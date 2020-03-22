package com.alnt.policyengine.domain.dto;


import com.alnt.platform.base.domain.dto.DTO;


public class MitigationControlOwnerGroupDTO extends DTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long mitigationControlId;
	
	private String ownerId;

	public Long getMitigationControlId() {
		return mitigationControlId;
	}

	public void setMitigationControlId(Long mitigationControlId) {
		this.mitigationControlId = mitigationControlId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	

}
