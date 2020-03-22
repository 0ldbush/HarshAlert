package com.alnt.policyengine.domain.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

public class RiskDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

	@JsonView(JsonViews.Medium.class)
	private String currency;

	@JsonView(JsonViews.Header.class)
	private String severity;

	@JsonView(JsonViews.Medium.class)
	private BigDecimal impact;
	
	@JsonView(JsonViews.Header.class)
	private Boolean assignDefault;

	@JsonView(JsonViews.Header.class)
	private List<MitigationControlDTO> mitigationControls = new ArrayList<>();

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

	public List<MitigationControlDTO> getMitigationControls() {
		return mitigationControls;
	}

	public void setMitigationControls(List<MitigationControlDTO> mitigationControls) {
		this.mitigationControls = mitigationControls;
	}

	public Boolean getAssignDefault() {
		return assignDefault;
	}

	public void setAssignDefault(Boolean assignDefault) {
		this.assignDefault = assignDefault;
	}

}
