package com.alnt.policyengine.domain.dto;

import java.util.List;


public class RuleConditionDTO {
	
private String logicalOperator;
private String operator;
private List<String> value;
private Integer range;
private String attribute;
private Boolean prependLogicalOperator;
private Boolean leaf;
private List<RuleConditionDTO> children;

public String getLogicalOperator() {
	return logicalOperator;
}
public void setLogicalOperator(String logicalOperator) {
	this.logicalOperator = logicalOperator;
}
public String getOperator() {
	return operator;
}
public void setOperator(String operator) {
	this.operator = operator;
}
public List<String> getValue() {
	return value;
}
public void setValue(List<String> value) {
	this.value = value;
}
public String getAttribute() {
	return attribute;
}
public void setAttribute(String attribute) {
	this.attribute = attribute;
}
public Boolean getPrependLogicalOperator() {
	return prependLogicalOperator;
}
public void setPrependLogicalOperator(Boolean prependLogicalOperator) {
	this.prependLogicalOperator = prependLogicalOperator;
}
public Boolean getLeaf() {
	return leaf;
}
public void setLeaf(Boolean leaf) {
	this.leaf = leaf;
}
public List<RuleConditionDTO> getChildren() {
	return children;
}
public void setChildren(List<RuleConditionDTO> children) {
	this.children = children;
}
public Integer getRange() {
	return range;
}
public void setRange(Integer range) {
	this.range = range;
}


}
