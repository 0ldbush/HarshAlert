package com.alnt.access.user.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLDelete;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.platform.base.domain.BaseEntity;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "USERS")
@SQLDelete(sql = "UPDATE USERS SET INT_STATUS = 3 WHERE ID = ?")
public class User extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "USER_NAME", unique = true)
	protected String userName;

	@Column(name = "USER_TYPE")
	protected String userType;

	@Column(name = "PASSWORD")
	protected String password;

	@Column(name = "PRIMARY_EMAIL_ADDRESS")
	protected String primaryEmail;

	@Column(name = "PRIMARY_PHONE")
	protected String primaryPhone;

	@Column(name = "FIRST_NAME")
	protected String firstName;

	@Column(name = "LAST_NAME")
	protected String lastName;

	@Column(name = "PASSWORD_CHANGE_REQUIRED")
	protected Boolean passwordChangeRequired = false;

	@Column(name = "LOCALE_CODE")
	protected String localeCode;

    @Column(name="DATE_FORMAT")
    private String dateFormat;

    @Column(name="TIME_FORMAT")
    private String timeFormat;
    
    @Column(name="NUMBER_FORMAT")
    private String numberFormat;

    @Column(name="TIME_ZONE")
    private String timeZone;

    @Column(name="START_ACTIVITY")
    private String startActivity;
    
    @Column(name="START_LAST_ACTIVITY")
    private Boolean startLastActivity; 
    
    @Column(name="KEEP_MESSAGES")
    private Boolean keepMessages; 
    
	@ManyToMany(fetch = FetchType.EAGER, targetEntity = JobRole.class, cascade = CascadeType.REFRESH)
	@JoinTable(name = "USER_JOB_ROLE", joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "JOB_ROLE_ID", referencedColumnName = "ID"))
	@Fetch(value = FetchMode.SUBSELECT)
	protected List<JobRole> roles = new ArrayList<JobRole>();

	@Column(name="RESET_PASSWORD")
	private Boolean resetPassword;

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!getClass().isAssignableFrom(obj.getClass())) {
			return false;
		}
		User other = (User) obj;

		if (id != null && other.id != null) {
			return id.equals(other.id);
		}

		if (userName == null) {
			if (other.userName != null) {
				return false;
			}
		} else if (!userName.equals(other.userName)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Boolean getPasswordChangeRequired() {
		return passwordChangeRequired;
	}

	public void setPasswordChangeRequired(Boolean passwordChangeRequired) {
		this.passwordChangeRequired = passwordChangeRequired;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
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

	public List<JobRole> getRoles() {
		return roles;
	}

	public void setRoles(List<JobRole> roles) {
		this.roles = roles;
	}

	public Boolean getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
}
