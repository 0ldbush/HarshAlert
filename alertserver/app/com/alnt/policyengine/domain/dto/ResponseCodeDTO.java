package com.alnt.policyengine.domain.dto;

import com.alnt.platform.base.domain.dto.BaseMasterDTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.platform.core.lists.domain.dto.ListEntriesDTO;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Header.class)
public class ResponseCodeDTO extends BaseMasterDTO {

	private static final long serialVersionUID = 1L;

	@JsonView(JsonViews.Header.class)
	private String responseAction;

	@JsonView(JsonViews.Header.class)
	private String responseActionType;

	@JsonView(JsonViews.Medium.class)
	private ListEntriesDTO responseActionTypeDetail;

	@JsonView(JsonViews.Medium.class)
	private ListEntriesDTO responseActionDetail;

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
	public ListEntriesDTO getResponseActionTypeDetail() {
		return responseActionTypeDetail;
	}

	public void setResponseActionTypeDetail(ListEntriesDTO responseActionTypeDetail) {
		this.responseActionTypeDetail = responseActionTypeDetail;
	}

	public ListEntriesDTO getResponseActionDetail() {
		return responseActionDetail;
	}

	public void setResponseActionDetail(ListEntriesDTO responseActionDetail) {
		this.responseActionDetail = responseActionDetail;
	}

}
