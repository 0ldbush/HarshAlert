package com.alnt.policyengine.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import com.alnt.access.user.domain.User;
import com.alnt.platform.base.domain.BaseMasterEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "POLICY")
@SQLDelete(sql = "UPDATE POLICY SET INT_STATUS = 3 WHERE ID = ?")
public class Policy extends BaseMasterEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "PRIORITY")
	private int priority;

	@Column(name = "ENFORCEMENT")
	private String enforcement;

	@Column(name = "POLICYGROUP")
	private String policyGroup;

	@ManyToOne(targetEntity = PolicyType.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "TYPE", referencedColumnName = "EXT_ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private PolicyType policyType;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = RuleSet.class, cascade = CascadeType.DETACH)
	@JoinTable(name = "POLICY_RULE_SET_XREF", joinColumns = @JoinColumn(name = "POLICY_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "RULE_SET_ID", referencedColumnName = "ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<RuleSet> ruleSets = new ArrayList<RuleSet>();

	/*@OneToMany(fetch = FetchType.LAZY, targetEntity = PolicyRuleSetXref.class, mappedBy = "policy", cascade = CascadeType.ALL)
	@BatchSize(size = 50)
	@NotFound(action = NotFoundAction.IGNORE)
	protected List<PolicyRuleSetXref> policyRuleSetXrefs = new ArrayList<PolicyRuleSetXref>();*/

	
	@ManyToOne(targetEntity = User.class, optional = true, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "OWNER", referencedColumnName = "ID", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private User owner;
	
	@Column(name = "OWNER")
	private Long ownerId;

	public int getPriority() {
		return priority;
	}

	public String getEnforcement() {
		return enforcement;
	}

	public String getPolicyGroup() {
		return policyGroup;
	}

	public PolicyType getPolicyType() {
		return policyType;
	}

	public List<RuleSet> getRuleSets() {
		return ruleSets;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setEnforcement(String enforcement) {
		this.enforcement = enforcement;
	}

	public void setPolicyGroup(String policyGroup) {
		this.policyGroup = policyGroup;
	}

	public void setPolicyType(PolicyType policyType) {
		this.policyType = policyType;
	}

	public void setRuleSets(List<RuleSet> ruleSets) {
		this.ruleSets = ruleSets;
	}

	/*public List<PolicyRuleSetXref> getPolicyRuleSetXrefs() {
		return policyRuleSetXrefs;
	}

	public void setPolicyRuleSetXrefs(List<PolicyRuleSetXref> policyRuleSetXrefs) {
		this.policyRuleSetXrefs = policyRuleSetXrefs;
	}*/

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
