package com.alnt.access.user.domain.dto;

import com.alnt.platform.base.domain.dto.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class PasswordChangeDTO extends BaseDTO {

	@JsonProperty(access = Access.WRITE_ONLY)
	protected String password;

	@JsonProperty(access = Access.WRITE_ONLY)
	protected String newPassword;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
