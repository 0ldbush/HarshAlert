package com.alnt.platform.core.navigation.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseSettingEntity;


// Table layout and filters personalized by  user

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "SEARCH_LAYOUT")
@SQLDelete(sql = "UPDATE SEARCH_LAYOUT SET INT_STATUS = 3 WHERE ID = ?")

public class SearchLayout extends BaseSettingEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Name of  view or grid component this search layout applies to	
	@Column(name="VIEW_ID")
    private String viewId;

	//Details (as binary data) or table layout, columns, filters & sort conditions
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "DETAILS", length = 50000)
	private String details;

	//Activity of screen control set	
	@Column(name="ACTIVITY_ID")
    private String activityId;

	//Indicates this table layout and filters should be applied by default	
	@Column(name="PREFERRED")
    private boolean preferred;

	//Indicates this table layout and filters should apply to all users	
	@Column(name="SHARED")
    private boolean shared;

	//Where conditions or filters applied by default to search grid	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name = "RULE_CRITERIA", length = 50000)
	private String ruleCriteria;

	//Fields to sort the table by (ascending or descending)	
	@Column(name="SORT_BY", length=255)
	private String sortBy;

	//Single column or field used to group results	
	@Column(name="GROUP_BY")
	private String groupBy;

	//Template  used to download table data to Excel	
	@Column(name="DOWNLOAD_TEMPLATE")
	private String downloadTemplate;

	//Indicates that tree-column nodes are expanded by default (tree-grid only)	
	@Column(name="EXPAND_ALL")
	private boolean expandAll;

	public String getViewId() {
		return viewId;
	}

	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public boolean isPreferred() {
		return preferred;
	}

	public void setPreferred(boolean preferred) {
		this.preferred = preferred;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getRuleCriteria() {
		return ruleCriteria;
	}

	public void setRuleCriteria(String ruleCriteria) {
		this.ruleCriteria = ruleCriteria;
	}

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public String getGroupBy() {
		return groupBy;
	}

	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	public String getDownloadTemplate() {
		return downloadTemplate;
	}

	public void setDownloadTemplate(String downloadTemplate) {
		this.downloadTemplate = downloadTemplate;
	}

	public boolean isExpandAll() {
		return expandAll;
	}

	public void setExpandAll(boolean expandAll) {
		this.expandAll = expandAll;
	}	
	
}