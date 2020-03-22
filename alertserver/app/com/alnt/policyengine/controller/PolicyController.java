package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.service.PolicyService;

import play.libs.concurrent.HttpExecutionContext;

public class PolicyController extends BaseController<Policy, PolicyDTO> {
	
	
		
	@Inject
	public PolicyController(PolicyService userService, HttpExecutionContext ec) {
		super(userService, ec, Policy.class, PolicyDTO.class);
	}

}
