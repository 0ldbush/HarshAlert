package com.alnt.platform.core.navigation.domain.dto;



import com.alnt.platform.base.domain.dto.BaseSettingDTO;


public class ActivityDTO extends BaseSettingDTO{
	
	private static final long serialVersionUID = 4522522422135450949L;

	private String busObjCat;
	
	private String description;
	
	private String title;
	
	private String followOns;

	private String helpTextURL;

	private String keywords;

	private String busFunction;
	
	private String mappedNamespace;

	private String defaultMode;

	private Boolean displayOnly;

	private Boolean settingAct;

	private Boolean hidden;

	private String fullNamespace;
	
	private String icon;
	
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
