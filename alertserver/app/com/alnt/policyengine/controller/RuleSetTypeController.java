package com.alnt.policyengine.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.RuleSetType;
import com.alnt.policyengine.domain.dto.RuleSetTypeDTO;
import com.alnt.policyengine.service.RuleSetTypeService;

import play.libs.concurrent.HttpExecutionContext;

public class RuleSetTypeController extends BaseController<RuleSetType, RuleSetTypeDTO> {

	@Inject
	public RuleSetTypeController(RuleSetTypeService ruleSetTypeService, HttpExecutionContext ec) {
		super(ruleSetTypeService, ec, RuleSetType.class, RuleSetTypeDTO.class);
	}

}
