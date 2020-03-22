package com.alnt.policyengine.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Combo.class)
public class RuleDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

	@JsonView(JsonViews.Header.class)
	private Integer priority;

	@JsonView(JsonViews.Medium.class)
	private String namespace;

	@JsonView(JsonViews.Medium.class)
	private String condition;

	@JsonView(JsonViews.Medium.class)
	private String action;

	@JsonView(JsonViews.Medium.class)
	private Integer version;

	@JsonView(JsonViews.Medium.class)
	private String riskId;

	@JsonView(JsonViews.Header.class)
	private RiskDTO risk;

	@JsonView(JsonViews.Header.class)
	private String conditionJson;
	
	@JsonView(JsonViews.Header.class)
	protected List<ResponseCodeDTO> responseCodes = new ArrayList<>();

	@JsonView(JsonViews.Header.class)
	protected List<RiskDTO> risks = new ArrayList<>();

	@JsonView(JsonViews.Medium.class)
	private Boolean advanceBuilder;

	@JsonView(JsonViews.Medium.class)
	private ClassDef classDef;

	@JsonView(JsonViews.Medium.class)
	private String entityId;

	@JsonView(JsonViews.Header.class)
	private String enforcement;

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public ClassDef getClassDef() {
		return classDef;
	}

	public void setClassDef(ClassDef classDef) {
		this.classDef = classDef;
	}

	public String getCondition() {
		return condition;
	}

	public String getAction() {
		return action;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Integer getPriority() {
		return priority;
	}

	public String getNamespace() {
		return namespace;
	}

	public Integer getVersion() {
		return version;
	}

	public String getRiskId() {
		return riskId;
	}

	public RiskDTO getRisk() {
		return risk;
	}
	
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public void setRiskId(String riskId) {
		this.riskId = riskId;
	}

	public void setRisk(RiskDTO risk) {
		this.risk = risk;
	}


	public Boolean getAdvanceBuilder() {
		return advanceBuilder;
	}

	public void setAdvanceBuilder(Boolean advanceBuilder) {
		this.advanceBuilder = advanceBuilder;
	}


	public String getConditionJson() {
		return conditionJson;
	}

	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

	public String getEnforcement() {
		return enforcement;
	}

	public void setEnforcement(String enforcement) {
		this.enforcement = enforcement;
	}
	
	public List<ResponseCodeDTO> getResponseCodes() {
		return responseCodes;
	}

	public void setResponseCodes(List<ResponseCodeDTO> responseCodes) {
		this.responseCodes = responseCodes;
	}


	public List<RiskDTO> getRisks(){
		return risks;
	}

	public void setRisks(List<RiskDTO> risks){
		this.risks = risks;
	}

}
