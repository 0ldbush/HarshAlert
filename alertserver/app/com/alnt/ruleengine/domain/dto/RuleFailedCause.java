package com.alnt.ruleengine.domain.dto;

import java.util.Set;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Combo.class)
public class RuleFailedCause {

//	private String type;
	
	private String attribute;
	private Set<String> value;
	public String getAttribute() {
		return attribute;
	}
	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}
	public Set<String> getValue() {
		return value;
	}
	public void setValue(Set<String> value) {
		this.value = value;
	}
	
	
	
}
