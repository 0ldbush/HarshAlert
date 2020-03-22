package com.alnt.platform.core.navigation.domain;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseEntity;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MENU_ITEM")
@SQLDelete(sql = "UPDATE MENU_ITEM SET INT_STATUS = 3 WHERE ID = ?")
public class MenuItem extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//[ReferenceKey:Menu.extID] Main menu (top level navigation option)	
	@Column(name="MENU_ID")
	private String menuId;
	
	@Column(name="JOB_ROLE_ID")
	private Long jobRoleId;

	//Indicates menu is at top level
	@Column(name="MAIN")
	private Boolean main;

	@Column(name="PARENT_ID")
	private String parentId;
	
	//Order of menu (top or second level)
	@Column(name="SEQ")
	private Integer seq;

	//Menu or menu item text	
	@Column(name="LABEL")
	private String label;

	@Column(name="MENU_CLS")
	private String menuCls;

	@Column(name="ICON_PATH")
	private String iconPath;

	@Column(name="SUBMENU_ICON_PATH")
	private String subMenuIconPath;

	//Default menu to load if no other default specified	
	@Column(name="PREFERRED")
	private Boolean preferred;

	//[ReferenceKey:Lists.listEntries.entryID?AccessLevel] Indicates how app can be accessed e.g. display, create, edit, delete	
	@Column(name="ACCESS_LEVEL")
	private String accessLevel;

	
    @Column(name="ACTIVITY_ID")
    private String activityId;
    
    @ManyToOne(targetEntity = Activity.class, cascade = CascadeType.REFRESH)
    @JoinColumn(name="ACTIVITY_ID", referencedColumnName="EXT_ID", foreignKey = @ForeignKey, insertable= false, updatable=false)
    private Activity activity;
    

	//[ReferenceKey:ScreenControl.menuItemRef] Screen layout indicating hidden etc. fields based on menu authorization	
	@Column(name="SCREEN_CONTROL_ID")
	private String screenControlId;

	//[ReferenceKey:Query.extID] For menu items as report - name of report assigned	
	@Column(name="REPORT_ID")
	private String reportId;

	//Access is from outside office/firewall	
	@Column(name="EXTERNAL_ACCESS")
	private Boolean external;	
	

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="PARENT_ID", referencedColumnName="MENU_ID", foreignKey = @ForeignKey)
	private List<MenuItem> subNavs = new ArrayList<MenuItem>();


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
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


	public Activity getActivity() {
		return activity;
	}


	public void setActivity(Activity activity) {
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


	public List<MenuItem> getSubNavs() {
		return subNavs;
	}


	public void setSubNavs(List<MenuItem> subNavs) {
		this.subNavs = subNavs;
	}


	public Long getJobRoleId() {
		return jobRoleId;
	}


	public void setJobRoleId(Long jobRoleId) {
		this.jobRoleId = jobRoleId;
	}	
	
}
