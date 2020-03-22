package com.alnt.policyengine.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.RiskType;
import com.alnt.policyengine.domain.dto.RiskTypeDTO;
import com.alnt.policyengine.service.RiskTypeService;

import play.libs.concurrent.HttpExecutionContext;

public class RiskTypeController extends BaseController<RiskType, RiskTypeDTO> {

	@Inject
	public RiskTypeController(RiskTypeService riskTypeService, HttpExecutionContext ec) {
		super(riskTypeService, ec, RiskType.class, RiskTypeDTO.class);
	}

}
