package com.alnt.ruleengine.domain.dto;

import java.util.Set;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonView(JsonViews.Combo.class)
public class RuleFailedCause {

//	private String type;
	
	private String attribute;
	private Set<String> value;
	
}
