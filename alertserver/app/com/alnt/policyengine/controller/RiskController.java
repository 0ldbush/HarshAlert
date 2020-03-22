package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.Risk;
import com.alnt.policyengine.domain.dto.RiskDTO;
import com.alnt.policyengine.service.RiskService;

import play.libs.concurrent.HttpExecutionContext;

public class RiskController extends BaseController<Risk, RiskDTO> {
	
	
	@Inject
	public RiskController(RiskService riskRuleService, HttpExecutionContext ec) {
		super(riskRuleService, ec, Risk.class, RiskDTO.class);
	}

}
