package com.alnt.workflow.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.alnt.platform.base.persistence.CacheConstants;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "WORKFLOW_RECIPIENT")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
public class WorkflowRecipient extends com.alnt.platform.base.domain.Entity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="WORKFLOW_RECIPIENT_CODE")
	private String workflowRecipientCode;

	@Column(name = "SEQ")
	private Integer seq;

	@Column(name = "RECIPIENT_RANK")
	private Integer rank;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "ROLE_OBJECT")
	private String roleObject;

	@Column(name = "ROLE_FIELD")
	private String roleField;

	@Column(name = "ROLE_REFERENCE")
	private String roleReference;

	@Column(name = "ROLE_FORMULA")
	private String roleFormula;

	@Column(name = "ROLE_API")
	private String roleAPI;

	/**
	 * For reminders: specific recipients can receive reminders or escalations at
	 * different times. This is the recipient-specific lead time offset which can be
	 * a negative number if desired (in working time or normal business hours)
	 */
	@Column(name = "LTO_HRS")
	private Integer ltoHrs;

	/**
	 * For reminders: specific recipients can receive reminders or escalations at
	 * different times. This is the recipient-specific lead time offset which can be
	 * a negative number if desired (in calendar days)
	 */
	@Column(name = "LTO_DAYS")
	private Integer ltoDays;

	/**
	 * Name of workflow group, role or recipient (automatically generated but can be
	 * modified by user for informational purposes)
	 */
	@Column(name = "TEXT")
	private String text;

	/**
	 * Indicates if this workflow owner can edit or view the whole document or just
	 * the portions they own (when in workflow status they are responsible for.
	 * Values: Edit all, View All/Edit Own, Edit Own (cannot view remainder), View
	 * Own
	 */
	@Column(name = "AUTHORIZATION")
	private String authorization;

	@ManyToOne(targetEntity = Workflow.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "WORKFLOW_ID")
	protected Workflow workflow;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Workflow getWorkflow() {
		return workflow;
	}
	
	public Long getWorkflowId() {
		return workflow != null ? workflow.getId() : null;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public String getWorkflowRecipientCode() {
		return workflowRecipientCode;
	}

	public void setWorkflowRecipientCode(String workflowRecipientCode) {
		this.workflowRecipientCode = workflowRecipientCode;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getRoleAPI() {
		return roleAPI;
	}

	public void setRoleAPI(String roleAPI) {
		this.roleAPI = roleAPI;
	}

	
}
