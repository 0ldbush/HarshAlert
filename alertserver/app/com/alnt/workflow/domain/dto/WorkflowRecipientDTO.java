package com.alnt.workflow.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;

public class WorkflowRecipientDTO extends DTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Long id;

	private String workflowRecipientCode;

	private Integer seq;

	private Integer rank;

	private Long userId;

	private Long customerId;

	private String roleObject;

	private String roleField;

	private String roleReference;

	private String roleFormula;

	private Integer ltoHrs;

	private Integer ltoDays;

	private String text;

	private String authorization;

	protected Long workflowId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWorkflowRecipientCode() {
		return workflowRecipientCode;
	}

	public void setWorkflowRecipientCode(String workflowRecipientCode) {
		this.workflowRecipientCode = workflowRecipientCode;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getRoleObject() {
		return roleObject;
	}

	public void setRoleObject(String roleObject) {
		this.roleObject = roleObject;
	}

	public String getRoleField() {
		return roleField;
	}

	public void setRoleField(String roleField) {
		this.roleField = roleField;
	}

	public String getRoleReference() {
		return roleReference;
	}

	public void setRoleReference(String roleReference) {
		this.roleReference = roleReference;
	}

	public String getRoleFormula() {
		return roleFormula;
	}

	public void setRoleFormula(String roleFormula) {
		this.roleFormula = roleFormula;
	}

	public Integer getLtoHrs() {
		return ltoHrs;
	}

	public void setLtoHrs(Integer ltoHrs) {
		this.ltoHrs = ltoHrs;
	}

	public Integer getLtoDays() {
		return ltoDays;
	}

	public void setLtoDays(Integer ltoDays) {
		this.ltoDays = ltoDays;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	
}
