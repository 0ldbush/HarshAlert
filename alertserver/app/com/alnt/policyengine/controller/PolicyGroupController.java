package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.PolicyGroup;
import com.alnt.policyengine.domain.dto.PolicyGroupDTO;
import com.alnt.policyengine.service.PolicyGroupService;

import play.libs.concurrent.HttpExecutionContext;

public class PolicyGroupController extends BaseController<PolicyGroup, PolicyGroupDTO> {
	
	
		
	@Inject
	public PolicyGroupController(PolicyGroupService userService, HttpExecutionContext ec) {
		super(userService, ec, PolicyGroup.class, PolicyGroupDTO.class);
	}

}
