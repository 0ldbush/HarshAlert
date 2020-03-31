package com.alnt.platform.core.lists.domain.dto;

import com.alnt.platform.base.domain.dto.BaseDTO;

public class ListEntriesDTO extends BaseDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6277309021573138384L;

    protected String entryCode;
    
    protected String entryName;
    
    protected String listCode;
    
    protected String entryType;

    

	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	public String getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	public String getEntryName() {
		//return DynamicTranslationProvider.getValue(this, "entryName", entryName);
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	
}
