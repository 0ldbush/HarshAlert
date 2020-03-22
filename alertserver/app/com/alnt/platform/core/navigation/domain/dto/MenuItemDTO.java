package com.alnt.platform.core.navigation.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseDTO;

public class MenuItemDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String menuId;
	
	private Long jobRoleId;

	private Boolean main;

	private String parentId;

	private Integer seq;

	private String label;
	
	private String menuCls;

	private String iconPath;

	private String subMenuIconPath;

	private Boolean preferred;

	private String accessLevel;

	private String activityId;

	private ActivityDTO activity;

	private String screenControlId;

	private String reportId;

	private Boolean external;

	private List<MenuItemDTO> subNavs = new ArrayList<MenuItemDTO>();

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public Long getJobRoleId() {
		return jobRoleId;
	}

	public void setJobRoleId(Long jobRoleId) {
		this.jobRoleId = jobRoleId;
	}

	public Boolean getMain() {
		return main;
	}

	public void setMain(Boolean main) {
		this.main = main;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getMenuCls() {
		return menuCls;
	}

	public void setMenuCls(String menuCls) {
		this.menuCls = menuCls;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getSubMenuIconPath() {
		return subMenuIconPath;
	}

	public void setSubMenuIconPath(String subMenuIconPath) {
		this.subMenuIconPath = subMenuIconPath;
	}

	public Boolean getPreferred() {
		return preferred;
	}

	public void setPreferred(Boolean preferred) {
		this.preferred = preferred;
	}

	public String getAccessLevel() {
		return accessLevel;
	}

	public void setAccessLevel(String accessLevel) {
		this.accessLevel = accessLevel;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public ActivityDTO getActivity() {
		return activity;
	}

	public void setActivity(ActivityDTO activity) {
		this.activity = activity;
	}

	public String getScreenControlId() {
		return screenControlId;
	}

	public void setScreenControlId(String screenControlId) {
		this.screenControlId = screenControlId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Boolean getExternal() {
		return external;
	}

	public void setExternal(Boolean external) {
		this.external = external;
	}

	public List<MenuItemDTO> getSubNavs() {
		return subNavs;
	}

	public void setSubNavs(List<MenuItemDTO> subNavs) {
		this.subNavs = subNavs;
	}	

}
