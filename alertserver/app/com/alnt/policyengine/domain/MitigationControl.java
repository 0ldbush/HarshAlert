package com.alnt.policyengine.domain;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MITIGATION_CONTROL")
@SQLDelete(sql = "UPDATE MITIGATION_CONTROL SET INT_STATUS = 3 WHERE ID = ?")
public class MitigationControl extends BaseMasterEntity {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "validity_days")
	private Integer validityDays;
	
	@OneToMany(fetch = FetchType.EAGER, targetEntity = MitigationControlOwner.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "MITIGATION_CONTROL_ID", referencedColumnName = "ID")
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<MitigationControlOwner> owners = new ArrayList<MitigationControlOwner>();

	@OneToMany(fetch = FetchType.EAGER, targetEntity = MitigationControlOwnerGroup.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "MITIGATION_CONTROL_ID", referencedColumnName = "ID")
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<MitigationControlOwnerGroup> ownerGroups = new ArrayList<MitigationControlOwnerGroup>();	
//	@ElementCollection
//    @CollectionTable(name = "MITIGATION_CONTROL_OWNER_XREF", joinColumns = @JoinColumn(name = "MITIGATION_CONTROL_ID"))
//    @Column(name = "OWNER_ID")
//    private List<String> owners = new ArrayList<String>();	
//	
//	@ElementCollection
//    @CollectionTable(name = "MITIGATION_CONTROL_OWNER_GROUP_XREF", joinColumns = @JoinColumn(name = "MITIGATION_CONTROL_ID"))
//    @Column(name = "OWNER_GROUP_ID")
//    private List<String> ownerGroups = new ArrayList<String>();  
	
	public Integer getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(Integer validityDays) {
		this.validityDays = validityDays;
	}

	public List<MitigationControlOwner> getOwners() {
		return owners;
	}

	public void setOwners(List<MitigationControlOwner> owners) {
		this.owners = owners;
	}

	public List<MitigationControlOwnerGroup> getOwnerGroups() {
		return ownerGroups;
	}

	public void setOwnerGroups(List<MitigationControlOwnerGroup> ownerGroups) {
		this.ownerGroups = ownerGroups;
	}	

}
