package com.alnt.policyengine.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

public class MitigationControlDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

	@JsonView(JsonViews.Header.class)
	private Integer validityDays;

	@JsonView(JsonViews.Header.class)
	private List<MitigationControlOwnerDTO> owners = new ArrayList<MitigationControlOwnerDTO>();

	@JsonView(JsonViews.Header.class)
	private List<MitigationControlOwnerGroupDTO> ownerGroups = new ArrayList<MitigationControlOwnerGroupDTO>();

	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}

	public List<MitigationControlOwnerDTO> getOwners() {
		return owners;
	}

	public void setOwners(List<MitigationControlOwnerDTO> owners) {
		this.owners = owners;
	}

	public List<MitigationControlOwnerGroupDTO> getOwnerGroups() {
		return ownerGroups;
	}

	public void setOwnerGroups(List<MitigationControlOwnerGroupDTO> ownerGroups) {
		this.ownerGroups = ownerGroups;
	}

}
