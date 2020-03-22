package com.alnt.platform.core.navigation.domain.dto;

import com.alnt.platform.base.domain.dto.BaseSettingDTO;


// Table layout and filters personalized by  user


public class SearchLayoutDTO extends BaseSettingDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private String viewId;

    private String details;

    private String activityId;

    private boolean preferred;

    private boolean shared;

	private String ruleCriteria;

	private String sortBy;

	private String groupBy;

	private String downloadTemplate;

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