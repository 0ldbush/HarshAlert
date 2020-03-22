package com.alnt.platform.core.lists.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseDTO;

public class ListsDTO extends BaseDTO{

	private static final long serialVersionUID = -2016879512892008364L;
    
    protected String listCode;

    protected String description;

    protected List<ListEntriesDTO> listEntries = new ArrayList<>();


	public String getListCode() {
		return listCode;
	}

	public void setListCode(String listCode) {
		this.listCode = listCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ListEntriesDTO> getListEntries() {
		return listEntries;
	}

	public void setListEntries(List<ListEntriesDTO> listEntries) {
		this.listEntries = listEntries;
	}
    
}
