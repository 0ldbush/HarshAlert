package com.alnt.platform.core.docnumberrange.domain.dto;

import java.util.Date;

import com.alnt.platform.base.domain.dto.BaseDTO;

public class DocNumberDTO extends BaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String numRangeId;

	private Integer fiscalYear;

	private String docNumber;

	private Date numRangeResetDate;

	public String getNumRangeId() {
		return numRangeId;
	}

	public void setNumRangeId(String numRangeId) {
		this.numRangeId = numRangeId;
	}

	public Integer getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(Integer fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	public Date getNumRangeResetDate() {
		return numRangeResetDate;
	}

	public void setNumRangeResetDate(Date numRangeResetDate) {
		this.numRangeResetDate = numRangeResetDate;
	}	

}
