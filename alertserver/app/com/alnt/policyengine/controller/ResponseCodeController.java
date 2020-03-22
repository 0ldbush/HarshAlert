package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.ResponseCode;
import com.alnt.policyengine.domain.dto.ResponseCodeDTO;
import com.alnt.policyengine.service.ResponseCodeService;

import play.libs.concurrent.HttpExecutionContext;

public class ResponseCodeController extends BaseController<ResponseCode, ResponseCodeDTO> {
	
	
		
	@Inject
	public ResponseCodeController(ResponseCodeService responseCodeService, HttpExecutionContext ec) {
		super(responseCodeService, ec, ResponseCode.class, ResponseCodeDTO.class);
	}

}
