package com.alnt.policyengine.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MITIGATION_CONTROL_OWNER_GROUP")
public class MitigationControlOwnerGroup extends com.alnt.platform.base.domain.Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "MITIGATION_CONTROL_ID")
	private Long mitigationControlId;
	
	@Column(name = "OWNER_ID", length = 50)
	private String ownerId;

	public Long getMitigationControlId() {
		return mitigationControlId;
	}

	public void setMitigationControlId(Long mitigationControlId) {
		this.mitigationControlId = mitigationControlId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	

}
