package com.alnt.mdm.ruleattr.controller;


import javax.inject.Inject;

import com.alnt.mdm.ruleattr.domain.RuleAttr;
import com.alnt.mdm.ruleattr.domain.dto.RuleAttrDTO;
import com.alnt.mdm.ruleattr.service.RuleAttrService;
import com.alnt.platform.base.controller.BaseController;

import play.libs.concurrent.HttpExecutionContext;

public class RuleAttrController extends BaseController<RuleAttr, RuleAttrDTO> {
	
			
	@Inject
	public RuleAttrController(RuleAttrService ruleAttrService, HttpExecutionContext ec) {
		super(ruleAttrService, ec, RuleAttr.class, RuleAttrDTO.class);
	}

}
