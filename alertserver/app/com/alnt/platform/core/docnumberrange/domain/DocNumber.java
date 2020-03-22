package com.alnt.platform.core.docnumberrange.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "DOC_NUMBER")
@SQLDelete(sql = "UPDATE DOC_NUMBER SET INT_STATUS = 3 WHERE ID = ?")
public class DocNumber extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "NUM_RANGE_ID")
	private String numRangeId;

	@Column(name = "FISCAL_YEAR")
	private Integer fiscalYear;

	//[RefElementID:CurrentDocNumber] Number of latest document
	@Column(name = "DOC_NUMBER")
	private String docNumber;

	//Start date for document numbering - used to predict when range will be exhausted	
	@Column(name = "RANGE_RESET_DATE")
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
