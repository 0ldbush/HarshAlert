package com.alnt.identity.user.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "afbcuser_det")
@Immutable
public class IdentityUser extends com.alnt.platform.base.domain.Entity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	protected Long id;

	
	
	public Long getId() {
		return id;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Long getActive() {
		return active;
	}

	public void setActive(Long active) {
		this.active = active;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getExtId() {
		return extId;
	}




	public void setExtId(String extId) {
		this.extId = extId;
	}




	public String getText() {
		return text;
	}




	public void setText(String text) {
		this.text = text;
	}




	public void setId(Long id) {
		this.id = id;
	}

	private static final long serialVersionUID = 1L;


	@Column(name = "user_name")
	private String extId;

	@Column(name = "firstName")
	private String text;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;

	@Column(name = "active")
	private Long active;



}
