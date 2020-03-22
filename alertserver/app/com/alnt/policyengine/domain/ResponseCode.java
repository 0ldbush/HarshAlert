package com.alnt.policyengine.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.core.lists.domain.ListEntries;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "RESPONSE_CODE")
@SQLDelete(sql = "UPDATE RESPONSE_CODE SET INT_STATUS = 3 WHERE ID = ?")
public class ResponseCode extends BaseMasterEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "RESPONSE_ACTION")
	private String responseAction;

	@Column(name = "RESPONSE_ACTION_TYPE")
	private String responseActionType;

	@ManyToOne(targetEntity = ListEntries.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "RESPONSE_ACTION", referencedColumnName = "ENTRY_CODE", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private ListEntries responseActionDetail;

	@ManyToOne(targetEntity = ListEntries.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "RESPONSE_ACTION_TYPE", referencedColumnName = "ENTRY_CODE", foreignKey = @ForeignKey, insertable = false, updatable = false)
	private ListEntries responseActionTypeDetail;

	public String getResponseAction() {
		return responseAction;
	}

	public void setResponseAction(String responseAction) {
		this.responseAction = responseAction;
	}

	public String getResponseActionType() {
		return responseActionType;
	}

	public void setResponseActionType(String responseActionType) {
		this.responseActionType = responseActionType;
	}

	public ListEntries getResponseActionDetail() {
		return responseActionDetail;
	}

	public void setResponseActionDetail(ListEntries responseActionDetail) {
		this.responseActionDetail = responseActionDetail;
	}

	public ListEntries getResponseActionTypeDetail() {
		return responseActionTypeDetail;
	}

	public void setResponseActionTypeDetail(ListEntries responseActionTypeDetail) {
		this.responseActionTypeDetail = responseActionTypeDetail;
	}

}
