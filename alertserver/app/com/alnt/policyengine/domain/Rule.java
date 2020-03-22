package com.alnt.policyengine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.core.classdef.domain.ClassDef;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RULES")
@SQLDelete(sql = "UPDATE RULES SET INT_STATUS = 3 WHERE ID = ?")
public class Rule extends BaseMasterEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NAMESPACE")
	private String namespace;

	@Column(name = "CONDITION_JSON", length = 2000)
	private String conditionJson;

	@Column(name = "CONDITION", length = 2000)
	private String condition;

	@Column(name = "ACTION", length = 2000)
	private String action;

	@Column(name = "PRIORITY")
	private Integer priority;

	@Column(name = "RISK_ID")
	private String riskId;

	@Column(name = "ADVANCEBUILDER")
	private Boolean advanceBuilder;

	@Column(name = "RESPONSE_CODE_ID")
	private String responseCodeId;

	@Column(name = "ENTITY_ID")
	private String entityId;

	@ManyToOne(targetEntity = ClassDef.class, optional = true, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "ENTITY_ID", referencedColumnName = "EXT_ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private ClassDef classDef;

	// @ManyToOne(targetEntity = Risk.class, optional = true, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "RISK_ID", referencedColumnName = "EXT_ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	// private Risk risk;


// @ManyToMany(fetch = FetchType.EAGER, targetEntity = RuleSet.class, cascade = CascadeType.DETACH)
// 	@JoinTable(name = "POLICY_RULE_SET_XREF", joinColumns = @JoinColumn(name = "POLICY_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID"))
// 	@Fetch(value = FetchMode.SUBSELECT)
// 	protected List<RuleSet> ruleSets = new ArrayList<RuleSet>();


	@ManyToMany(fetch = FetchType.EAGER, targetEntity = ResponseCode.class, cascade = CascadeType.DETACH)
	@JoinTable(name = "RULE_RESPONSE_CODE_XREF", joinColumns = @JoinColumn(name = "RULE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "RESPONSE_CODE_ID", referencedColumnName = "ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<ResponseCode> responseCodes = new ArrayList<ResponseCode>();

	public List<ResponseCode> getResponseCodes() {
		return responseCodes;
	}

	public void setResponseCodes(List<ResponseCode> responseCodes) {
		this.responseCodes = responseCodes;
	}


	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Risk.class, cascade = CascadeType.DETACH)
	@JoinTable(name = "RULE_RISK_XREF", joinColumns = @JoinColumn(name = "RULE_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "RISK_ID", referencedColumnName = "ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<Risk> risks = new ArrayList<Risk>();

	public List<Risk> getRisks() {
		return this.risks;
	}

	public void setRisks(List<Risk> risks) {
		this.risks = risks;
	}

	// @ManyToOne(targetEntity = ResponseCode.class, optional = true, cascade = CascadeType.REFRESH)
	// @JoinColumn(name = "RESPONSE_CODE_ID", referencedColumnName = "EXT_ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	//	private ResponseCode responseCode;

	@Column(name = "ENFORCEMENT")
	private String enforcement;

	public String getNamespace() {
		return namespace;
	}

	public String getConditionJson() {
		return conditionJson;
	}

	public String getCondition() {
		return condition;
	}

	public String getAction() {
		return action;
	}


	public Integer getPriority() {
		return priority;
	}

	public String getRiskId() {
		return riskId;
	}

	public String getResponseCodeId() {
		return responseCodeId;
	}

	

//	public ResponseCode getResponseCode() {
//		return responseCode;
//	}

	public String getEntityId() {
		return entityId;
	}

	public ClassDef getClassDef() {
		return classDef;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setConditionJson(String conditionJson) {
		this.conditionJson = conditionJson;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	// public void setRiskId(String riskId) {
	// 	this.riskId = riskId;
	// }

	public Boolean getAdvanceBuilder() {
		return advanceBuilder;
	}

	public void setAdvanceBuilder(Boolean advanceBuilder) {
		this.advanceBuilder = advanceBuilder;
	}

	public void setResponseCodeId(String responseCodeId) {
		this.responseCodeId = responseCodeId;
	}

	

//	public void setResponseCode(ResponseCode responseCode) {
//		this.responseCode = responseCode;
//	}

	// public Risk getRisk() {
	// 	return this.risk;
	// }

	// public void setRisk(Risk risk) {
	// 	this.risk = risk;
	// }

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public void setClassDef(ClassDef classDef) {
		this.classDef = classDef;
	}

	public String getEnforcement() {
		return enforcement;
	}

	public void setEnforcement(String enforcement) {
		this.enforcement = enforcement;
	}

}
