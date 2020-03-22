package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.RuleSet;
import com.alnt.policyengine.domain.dto.RuleSetDTO;
import com.alnt.policyengine.service.RuleSetService;

import play.libs.concurrent.HttpExecutionContext;

public class RuleSetController extends BaseController<RuleSet, RuleSetDTO> {
	
	
		
	@Inject
	public RuleSetController(RuleSetService ruleSetService, HttpExecutionContext ec) {
		super(ruleSetService, ec, RuleSet.class, RuleSetDTO.class);
	}

}
