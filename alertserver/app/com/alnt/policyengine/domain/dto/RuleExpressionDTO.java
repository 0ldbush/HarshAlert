package com.alnt.policyengine.domain.dto;

import java.util.List;


public class RuleExpressionDTO {
	
private String text;
private String description;
private String enforcement;
private String entityPrefix;
private List<RuleConditionDTO> ruleConditionDTOs;


public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getEnforcement() {
	return enforcement;
}
public void setEnforcement(String enforcement) {
	this.enforcement = enforcement;
}
public List<RuleConditionDTO> getRuleConditionDTOs() {
	return ruleConditionDTOs;
}
public void setRuleConditionDTOs(List<RuleConditionDTO> ruleConditionDTOs) {
	this.ruleConditionDTOs = ruleConditionDTOs;
}
public String getEntityPrefix() {
	return entityPrefix;
}
public void setEntityPrefix(String entityPrefix) {
	this.entityPrefix = entityPrefix;
}
public String getText() {
	return text;
}
public void setText(String text) {
	this.text = text;
}



}
