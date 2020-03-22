package com.alnt.platform.core.navigation.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseSettingEntity;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "ACTIVITY")
@SQLDelete(sql = "UPDATE ACTIVITY SET INT_STATUS = 3 WHERE ID = ?")
public class Activity extends BaseSettingEntity{
	
	private static final long serialVersionUID = 4522522422135450949L;


	@Column(name="BUS_OBJ_CAT")
	private String busObjCat;
	

	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="FOLLOW_ONS")
	private String followOns;

	@Column(name="HELP_TEXT_URL")
	private String helpTextURL;

	@Column(name="KEYWORDS")
	private String keywords;

	@Column(name="BUS_FUNCTION")
	private String busFunction;
	
	//Mapped name space	
	@Column(name="MAPPED_NAMESPACE")
	private String mappedNamespace;

	//Mapped name space	
	@Column(name="DEFAULT_MODE")
	private String defaultMode;

	//Mapped name space	
	@Column(name="DISPLAY_ONLY")
	private Boolean displayOnly;

	//Mapped name space	
	@Column(name="SETTING_ACT")
	private Boolean settingAct;

	@Column(name = "HIDDEN")
	private Boolean hidden;

	//Mapped name space	
	@Column(name="FULL_NAMESPACE")
	private String fullNamespace;
	
	//Activity icon or css class displayed when loading
	@Column(name="ICON")
	private String icon;

	//Mapped name space	
	@Column(name="PACKAGE_NAME")
	private String packageName;

	public String getBusObjCat() {
		return busObjCat;
	}

	public void setBusObjCat(String busObjCat) {
		this.busObjCat = busObjCat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFollowOns() {
		return followOns;
	}

	public void setFollowOns(String followOns) {
		this.followOns = followOns;
	}

	public String getHelpTextURL() {
		return helpTextURL;
	}

	public void setHelpTextURL(String helpTextURL) {
		this.helpTextURL = helpTextURL;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getBusFunction() {
		return busFunction;
	}

	public void setBusFunction(String busFunction) {
		this.busFunction = busFunction;
	}

	public String getMappedNamespace() {
		return mappedNamespace;
	}

	public void setMappedNamespace(String mappedNamespace) {
		this.mappedNamespace = mappedNamespace;
	}

	public String getDefaultMode() {
		return defaultMode;
	}

	public void setDefaultMode(String defaultMode) {
		this.defaultMode = defaultMode;
	}

	public Boolean getDisplayOnly() {
		return displayOnly;
	}

	public void setDisplayOnly(Boolean displayOnly) {
		this.displayOnly = displayOnly;
	}

	public Boolean getSettingAct() {
		return settingAct;
	}

	public void setSettingAct(Boolean settingAct) {
		this.settingAct = settingAct;
	}

	public Boolean getHidden() {
		return hidden;
	}

	public void setHidden(Boolean hidden) {
		this.hidden = hidden;
	}

	public String getFullNamespace() {
		return fullNamespace;
	}

	public void setFullNamespace(String fullNamespace) {
		this.fullNamespace = fullNamespace;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}	
	
	
	
}
