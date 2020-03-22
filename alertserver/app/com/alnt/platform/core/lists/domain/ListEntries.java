package com.alnt.platform.core.lists.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alnt.platform.base.persistence.CacheConstants;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="LIST_ENTRIES", indexes = {
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONFIG_MODULE_ELEMENTS)
public class ListEntries extends com.alnt.platform.base.domain.Entity implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Column(name="LIST_CODE")
   	private String listCode;

    @Column(name = "ENTRY_CODE", nullable=false)
    protected String entryCode;
    
    @Column(name = "ENTRY_NAME")
    protected String entryName;
    
    @Column(name = "ENTRY_TYPE")
    protected String entryType;
    

	public String getEntryCode() {
		return entryCode;
	}

	public void setEntryCode(String entryCode) {
		this.entryCode = entryCode;
	}

	public String getEntryName() {
		return entryName;
	}

	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}


	public String getEntryType() {
		return entryType;
	}

	public void setEntryType(String entryType) {
		this.entryType = entryType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((entryCode == null) ? 0 : entryCode.hashCode());
		result = prime * result + ((entryName == null) ? 0 : entryName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ListEntries other = (ListEntries) obj;
		if (entryCode == null) {
			if (other.entryCode != null)
				return false;
		} else if (!entryCode.equals(other.entryCode))
			return false;
		if (entryName == null) {
			if (other.entryName != null)
				return false;
		} else if (!entryName.equals(other.entryName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
    
    
    
}
