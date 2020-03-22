package com.alnt.policyengine.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.ResponseCodeType;
import com.alnt.policyengine.domain.dto.ResponseCodeTypeDTO;
import com.alnt.policyengine.service.ResponseCodeTypeService;

import play.libs.concurrent.HttpExecutionContext;

public class ResponseCodeTypeController extends BaseController<ResponseCodeType, ResponseCodeTypeDTO> {

	@Inject
	public ResponseCodeTypeController(ResponseCodeTypeService responseCodeService, HttpExecutionContext ec) {
		super(responseCodeService, ec, ResponseCodeType.class, ResponseCodeTypeDTO.class);
	}

}
