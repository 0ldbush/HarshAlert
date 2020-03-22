package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.PolicyType;
import com.alnt.policyengine.domain.dto.PolicyTypeDTO;
import com.alnt.policyengine.service.PolicyTypeService;

import play.libs.concurrent.HttpExecutionContext;

public class PolicyTypeController extends BaseController<PolicyType, PolicyTypeDTO> {
	
	
		
	@Inject
	public PolicyTypeController(PolicyTypeService policyTypeService, HttpExecutionContext ec) {
		super(policyTypeService, ec, PolicyType.class, PolicyTypeDTO.class);
	}

}
