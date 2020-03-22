package com.alnt.identity.user.domain.dto;

import java.util.Date;

import javax.persistence.Column;

import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.platform.base.domain.dto.BaseMasterDTO;

public class IdentityUserDTO extends BaseMasterDTO {
	


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	private String userName;

	private String firstName;

	private String lastName;

	private String email;

	private Long active;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	private String telephone;

	

}
