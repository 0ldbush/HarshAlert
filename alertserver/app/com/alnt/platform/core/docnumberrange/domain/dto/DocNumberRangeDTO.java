package com.alnt.platform.core.docnumberrange.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseSettingDTO;

public class DocNumberRangeDTO extends BaseSettingDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean fiscalYearRange = false;

	//Number range is dependent on, or varies by, company code	
	//private Boolean companyRange;

	//Category or kind of business object	
	private String busObjCat;

	//Controls whether the user can enter the document number or it is system generated. If user can optionally enter document ID then internal and external ranges, that do not overlap, should be defined	
	private String userInputOfExtId;

	//Start point for internally numbered documents. Can include alphas e.g. BG9001	
	private String internalRangeStart;

	//End point for internally numbered documents. Start/end points should match for letters in document numbering format that are "fixed". Leave blank for open ended numbering	
	private String internalRangeEnd;

	//Format or structure of document numbering. Controls how document numbers are incremented, including alphanumeric increments. Based on the following convention: AANNNN means first document is AA0001, then AA0002, up to AA9999, then AB0001 and so on (depending on values of start and end document). Please note that leading zeroes are removed before display if document is all numerics	
	private String internalRangeFormat;

	//Avoid missing numbers by ensuring a contiguous range	
	private Boolean noMissedNumbers=false;

	//Start point for externally numbered documents (user cannot input document number below this value)	
	private String externalRangeStart;

	//Max allowed entry for externally numbered documents	
	private String externalRangeEnd;

	//Format or structure of document numbering. Controls how document numbers are incremented, including alphanumeric increments. Based on the following convention: AANNNN means first document is AA0001, then AA0002, up to AA9999, then AB0001 and so on (depending on values of start and end document). Please note that leading zeroes are removed before display if document is all numerics	
	private String externalRangeFormat;

	//Multiple objects can have the same code (does not have to be unique)	
	private Boolean duplicatesAllowed=false;

	//Business document number or code can be changed after initial set-up	
	private Boolean codeEditAllowed=false;

	//Maximum field length	
	private Integer maxLength;

	//[RefElementID:RemoveLeadingZeros] Remove Leading Zeros	
	private Boolean removeLeadingZeros=true;
	
	private List<String> busObjTypes = new ArrayList<String>();

	public Boolean getFiscalYearRange() {
		return fiscalYearRange;
	}

	public void setFiscalYearRange(Boolean fiscalYearRange) {
		this.fiscalYearRange = fiscalYearRange;
	}

	/*public Boolean getCompanyRange() {
		return companyRange;
	}

	public void setCompanyRange(Boolean companyRange) {
		this.companyRange = companyRange;
	}*/

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public String getUserInputOfExtId() {
		return userInputOfExtId;
	}

	public void setUserInputOfExtId(String userInputOfExtId) {
		this.userInputOfExtId = userInputOfExtId;
	}

	public String getInternalRangeStart() {
		return internalRangeStart;
	}

	public void setInternalRangeStart(String internalRangeStart) {
		this.internalRangeStart = internalRangeStart;
	}

	public String getInternalRangeEnd() {
		return internalRangeEnd;
	}

	public void setInternalRangeEnd(String internalRangeEnd) {
		this.internalRangeEnd = internalRangeEnd;
	}

	public String getInternalRangeFormat() {
		return internalRangeFormat;
	}

	public void setInternalRangeFormat(String internalRangeFormat) {
		this.internalRangeFormat = internalRangeFormat;
	}

	public Boolean getNoMissedNumbers() {
		return noMissedNumbers;
	}

	public void setNoMissedNumbers(Boolean noMissedNumbers) {
		this.noMissedNumbers = noMissedNumbers;
	}

	public String getExternalRangeStart() {
		return externalRangeStart;
	}

	public void setExternalRangeStart(String externalRangeStart) {
		this.externalRangeStart = externalRangeStart;
	}

	public String getExternalRangeEnd() {
		return externalRangeEnd;
	}

	public void setExternalRangeEnd(String externalRangeEnd) {
		this.externalRangeEnd = externalRangeEnd;
	}

	public String getExternalRangeFormat() {
		return externalRangeFormat;
	}

	public void setExternalRangeFormat(String externalRangeFormat) {
		this.externalRangeFormat = externalRangeFormat;
	}

	public Boolean getDuplicatesAllowed() {
		return duplicatesAllowed;
	}

	public void setDuplicatesAllowed(Boolean duplicatesAllowed) {
		this.duplicatesAllowed = duplicatesAllowed;
	}

	public Boolean getCodeEditAllowed() {
		return codeEditAllowed;
	}

	public void setCodeEditAllowed(Boolean codeEditAllowed) {
		this.codeEditAllowed = codeEditAllowed;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public Boolean getRemoveLeadingZeros() {
		return removeLeadingZeros;
	}

	public void setRemoveLeadingZeros(Boolean removeLeadingZeros) {
		this.removeLeadingZeros = removeLeadingZeros;
	}

	public List<String> getBusObjTypes() {
		return busObjTypes;
	}

	public void setBusObjTypes(List<String> busObjTypes) {
		this.busObjTypes = busObjTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busObjCat == null) ? 0 : busObjCat.hashCode());
		result = prime * result + ((codeEditAllowed == null) ? 0 : codeEditAllowed.hashCode());
		result = prime * result + ((duplicatesAllowed == null) ? 0 : duplicatesAllowed.hashCode());
		result = prime * result + ((externalRangeEnd == null) ? 0 : externalRangeEnd.hashCode());
		result = prime * result + ((externalRangeFormat == null) ? 0 : externalRangeFormat.hashCode());
		result = prime * result + ((externalRangeStart == null) ? 0 : externalRangeStart.hashCode());
		result = prime * result + ((fiscalYearRange == null) ? 0 : fiscalYearRange.hashCode());
		result = prime * result + ((internalRangeEnd == null) ? 0 : internalRangeEnd.hashCode());
		result = prime * result + ((internalRangeFormat == null) ? 0 : internalRangeFormat.hashCode());
		result = prime * result + ((internalRangeStart == null) ? 0 : internalRangeStart.hashCode());
		result = prime * result + ((maxLength == null) ? 0 : maxLength.hashCode());
		result = prime * result + ((noMissedNumbers == null) ? 0 : noMissedNumbers.hashCode());
		result = prime * result + ((removeLeadingZeros == null) ? 0 : removeLeadingZeros.hashCode());
		result = prime * result + ((userInputOfExtId == null) ? 0 : userInputOfExtId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocNumberRangeDTO other = (DocNumberRangeDTO) obj;
		if (busObjCat == null) {
			if (other.busObjCat != null)
				return false;
		} else if (!busObjCat.equals(other.busObjCat))
			return false;
		if (codeEditAllowed == null) {
			if (other.codeEditAllowed != null)
				return false;
		} else if (!codeEditAllowed.equals(other.codeEditAllowed))
			return false;
		if (duplicatesAllowed == null) {
			if (other.duplicatesAllowed != null)
				return false;
		} else if (!duplicatesAllowed.equals(other.duplicatesAllowed))
			return false;
		if (externalRangeEnd == null) {
			if (other.externalRangeEnd != null)
				return false;
		} else if (!externalRangeEnd.equals(other.externalRangeEnd))
			return false;
		if (externalRangeFormat == null) {
			if (other.externalRangeFormat != null)
				return false;
		} else if (!externalRangeFormat.equals(other.externalRangeFormat))
			return false;
		if (externalRangeStart == null) {
			if (other.externalRangeStart != null)
				return false;
		} else if (!externalRangeStart.equals(other.externalRangeStart))
			return false;
		if (fiscalYearRange == null) {
			if (other.fiscalYearRange != null)
				return false;
		} else if (!fiscalYearRange.equals(other.fiscalYearRange))
			return false;
		if (internalRangeEnd == null) {
			if (other.internalRangeEnd != null)
				return false;
		} else if (!internalRangeEnd.equals(other.internalRangeEnd))
			return false;
		if (internalRangeFormat == null) {
			if (other.internalRangeFormat != null)
				return false;
		} else if (!internalRangeFormat.equals(other.internalRangeFormat))
			return false;
		if (internalRangeStart == null) {
			if (other.internalRangeStart != null)
				return false;
		} else if (!internalRangeStart.equals(other.internalRangeStart))
			return false;
		if (maxLength == null) {
			if (other.maxLength != null)
				return false;
		} else if (!maxLength.equals(other.maxLength))
			return false;
		if (noMissedNumbers == null) {
			if (other.noMissedNumbers != null)
				return false;
		} else if (!noMissedNumbers.equals(other.noMissedNumbers))
			return false;
		if (removeLeadingZeros == null) {
			if (other.removeLeadingZeros != null)
				return false;
		} else if (!removeLeadingZeros.equals(other.removeLeadingZeros))
			return false;
		if (userInputOfExtId == null) {
			if (other.userInputOfExtId != null)
				return false;
		} else if (!userInputOfExtId.equals(other.userInputOfExtId))
			return false;
		return true;
	}

}
