package com.alnt.platform.core.lists.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.persistence.CacheConstants;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="LISTS", indexes = {
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_CONFIG_MODULE_ELEMENTS)
@SQLDelete(sql="UPDATE LISTS SET INTSTATUS = 3 WHERE LISTS_ID = ?")
public class Lists extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Column(name = "CODE", nullable=false)
    protected String listCode;
    
    @Column(name = "DESCRIPTION")
    protected String description;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="LIST_CODE", referencedColumnName="CODE", foreignKey = @ForeignKey)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region=CacheConstants.REGION_CONFIG_MODULE_ELEMENTS)
    protected List<ListEntries> listEntries = new ArrayList<>();


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

	public List<ListEntries> getListEntries() {
		return listEntries;
	}

	public void setListEntries(List<ListEntries> listEntries) {
		this.listEntries = listEntries;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listCode == null) ? 0 : listCode.hashCode());
		result = prime * result + ((listEntries == null) ? 0 : listEntries.hashCode());
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
		Lists other = (Lists) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listCode == null) {
			if (other.listCode != null)
				return false;
		} else if (!listCode.equals(other.listCode))
			return false;
		if (listEntries == null) {
			if (other.listEntries != null)
				return false;
		} else if (!listEntries.equals(other.listEntries))
			return false;
		return true;
	}
    
    
    
    
}
