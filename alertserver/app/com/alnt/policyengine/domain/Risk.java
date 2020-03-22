package com.alnt.policyengine.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RISK")
@SQLDelete(sql = "UPDATE RISK SET INT_STATUS = 3 WHERE ID = ?")

public class Risk extends BaseMasterEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "CURRENCY")
	private String currency;

	@Column(name = "SEVERITY")
	private String severity;

	@Column(name = "IMPACT")
	private BigDecimal impact;
	
	@Column(name="ASSIGN_DEFAULT")
	private Boolean assignDefault;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = MitigationControl.class, cascade = CascadeType.REFRESH)
	@JoinTable(name = "RISK_MITIGATION_CONTROL_XREF", joinColumns = @JoinColumn(name = "RISK_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "MITIGATION_ID", referencedColumnName = "ID"))
	protected List<MitigationControl> mitigationControls = new ArrayList<>();

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public BigDecimal getImpact() {
		return impact;
	}

	public void setImpact(BigDecimal impact) {
		this.impact = impact;
	}

	public List<MitigationControl> getMitigationControls() {
		return mitigationControls;
	}

	public void setMitigationControls(List<MitigationControl> mitigationControls) {
		this.mitigationControls = mitigationControls;
	}

	public Boolean getAssignDefault() {
		return assignDefault;
	}

	public void setAssignDefault(Boolean assignDefault) {
		this.assignDefault = assignDefault;
	}

}
