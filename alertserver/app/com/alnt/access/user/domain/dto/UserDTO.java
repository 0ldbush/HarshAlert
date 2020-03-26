package com.alnt.access.user.domain.dto;

import com.alnt.access.jobrole.domain.dto.JobRoleDTO;
import com.alnt.platform.base.domain.dto.BaseDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonView;

import java.util.ArrayList;
import java.util.List;

import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Subject;

public class UserDTO extends BaseDTO  implements Subject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6295510311162156343L;

//	@JsonProperty(access = Access.READ_ONLY)
	private String userName;

	@JsonView(JsonViews.Header.class)
	private String userType;
	
	@JsonView(JsonViews.Header.class)
	private String userRole;

	@JsonView(JsonViews.Medium.class)
	private String primaryEmail;

	@JsonView(JsonViews.Medium.class)
	private String primaryPhone;

	@JsonView(JsonViews.Header.class)
	private String firstName;

	@JsonView(JsonViews.Header.class)
	private String lastName;
	
	@JsonProperty(access = Access.WRITE_ONLY)
	private String unencodedPassword;

	@JsonIgnore
	protected String password;
	
	@JsonIgnore
	private Boolean passwordChangeRequired = false;

	@JsonView(JsonViews.Full.class)
    private String dateFormat;

	@JsonView(JsonViews.Full.class)
    private String timeFormat;
    
	@JsonView(JsonViews.Full.class)
    private String numberFormat;

	@JsonView(JsonViews.Full.class)
    private String timeZone;

	@JsonView(JsonViews.Full.class)
    private String startActivity;
    
	@JsonView(JsonViews.Full.class)
    private Boolean startLastActivity; 
    
	@JsonView(JsonViews.Full.class)
    private Boolean keepMessages; 
    
	@JsonView(JsonViews.Full.class)
    protected List<JobRoleDTO> roles = new ArrayList<JobRoleDTO>();

	private Boolean resetPassword=false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}
		
	public String getPassword() {
		return password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getPrimaryEmail() {
		return primaryEmail;
	}

	public void setPrimaryEmail(String primaryEmail) {
		this.primaryEmail = primaryEmail;
	}

	public String getPrimaryPhone() {
		return primaryPhone;
	}

	public void setPrimaryPhone(String primaryPhone) {
		this.primaryPhone = primaryPhone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUnencodedPassword() {
		return unencodedPassword;
	}

	public void setUnencodedPassword(String unencodedPassword) {
		this.unencodedPassword = unencodedPassword;
	}

	public Boolean getPasswordChangeRequired() {
		return passwordChangeRequired;
	}

	public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
		this.passwordChangeRequired = passwordChangeRequired;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}

	public String getNumberFormat() {
		return numberFormat;
	}

	public void setNumberFormat(String numberFormat) {
		this.numberFormat = numberFormat;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public String getStartActivity() {
		return startActivity;
	}

	public void setStartActivity(String startActivity) {
		this.startActivity = startActivity;
	}

	public Boolean getStartLastActivity() {
		return startLastActivity;
	}

	public void setStartLastActivity(Boolean startLastActivity) {
		this.startLastActivity = startLastActivity;
	}

	public Boolean getKeepMessages() {
		return keepMessages;
	}

	public void setKeepMessages(Boolean keepMessages) {
		this.keepMessages = keepMessages;
	}

	public List<JobRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<JobRoleDTO> roles) {
		this.roles = roles;
	}

	public Boolean getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}

	@Override
	public List<? extends Permission> getPermissions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getIdentifier() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
}
