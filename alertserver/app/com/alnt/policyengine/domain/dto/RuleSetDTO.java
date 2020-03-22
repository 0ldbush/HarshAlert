package com.alnt.policyengine.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;


public class RuleSetDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;
	
	private Integer version;
	
	protected List<RuleDTO> rules = new ArrayList<>();


	public List<RuleDTO> getRules() {
		return rules;
	}

	public void setRules(List<RuleDTO> rules) {
		this.rules = rules;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
