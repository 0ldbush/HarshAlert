package com.alnt.ruleengine.domain.dto;

import java.util.List;

import com.alnt.platform.base.domain.dto.BaseDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Combo.class)
public class DefaultOutput extends BaseDTO  {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mitigationId;
	
	private Long criticality;

	private RuleDTO rule;
	
	
	private List<RuleFailedCause> cause;
	
	public RuleDTO getRule() {
		return rule;
	}

	public void setRule(RuleDTO rule) {
		this.rule = rule;
	}

	public DefaultOutput(RuleDTO rule) {
		
		this.rule = rule;
	}
	
   public DefaultOutput(RuleDTO rule,List<RuleFailedCause> cause) {
		
		this.rule = rule;
		this.cause=cause;
	}

	public String getMitigationId() {
		return mitigationId;
	}

	public Long getCriticality() {
		return criticality;
	}

	public void setMitigationID(String mitigationId) {
		this.mitigationId = mitigationId;
	}

	public void setCriticality(Long criticality) {
		this.criticality = criticality;
	}

	public List<RuleFailedCause> getCause() {
		return cause;
	}

	public void setCause(List<RuleFailedCause> cause) {
		this.cause = cause;
	}

	

	
}
