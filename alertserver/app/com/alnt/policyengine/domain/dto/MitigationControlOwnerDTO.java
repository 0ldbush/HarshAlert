package com.alnt.policyengine.domain.dto;


import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Combo.class)
public class MitigationControlOwnerDTO extends DTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonView(JsonViews.Header.class)
	private Long mitigationControlId;
	
	@JsonView(JsonViews.Header.class)
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
