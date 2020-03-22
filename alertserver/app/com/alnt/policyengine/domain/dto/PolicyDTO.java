package com.alnt.policyengine.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.platform.base.domain.dto.BaseMasterDTO;

public class PolicyDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

	private int priority;

	private String enforcement;

	private PolicyTypeDTO policyType;

	protected List<RuleSetDTO> ruleSets = new ArrayList<>();

	//private List<PolicyRuleSetXrefDTO> policyRuleSetXrefs = new ArrayList<PolicyRuleSetXrefDTO>();

	private String policyGroup;

	private UserDTO owner;
	
	private Long ownerId;

	public int getPriority() {
		return priority;
	}

	public String getEnforcement() {
		return enforcement;
	}

	public PolicyTypeDTO getPolicyType() {
		return policyType;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setEnforcement(String enforcement) {
		this.enforcement = enforcement;
	}

	public void setPolicyType(PolicyTypeDTO policyType) {
		this.policyType = policyType;
	}

	/*public List<RuleSetDTO> getRuleSets() {
		return policyRuleSetXrefs.stream().map(PolicyRuleSetXrefDTO::getRuleSet).collect(Collectors.toList());
	}

	public List<PolicyRuleSetXrefDTO> getPolicyRuleSetXrefs() {
		return policyRuleSetXrefs;
	}

	public void setPolicyRuleSetXrefs(List<PolicyRuleSetXrefDTO> policyRuleSetXrefs) {
		this.policyRuleSetXrefs = policyRuleSetXrefs;
	}*/

	public String getPolicyGroup() {
		return policyGroup;
	}

	public void setPolicyGroup(String policyGroup) {
		this.policyGroup = policyGroup;
	}

	public List<RuleSetDTO> getRuleSets() {
		return ruleSets;
	}

	public void setRuleSets(List<RuleSetDTO> ruleSets) {
		this.ruleSets = ruleSets;
	}

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

}
