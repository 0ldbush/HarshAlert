package com.alnt.platform.base.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class Entity  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "sequence_id", strategy = "com.alnt.platform.base.persistence.idgenerator.IDGenerator")
	@GeneratedValue(generator = "sequence_id")
	protected Long id;
	
	@Transient
	private boolean isNew;

	@Transient
	private String clientSequenceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getClientSequenceId() {
		return clientSequenceId;
	}

	public void setClientSequenceId(String clientSequenceId) {
		this.clientSequenceId = clientSequenceId;
	}

	public boolean getIsNew() {
		return isNew;
	}

	public void setIsNew(boolean isNew) {
		this.isNew = isNew;
	}



}
