package com.alnt.policyengine.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.MitigationControl;
import com.alnt.policyengine.domain.dto.MitigationControlDTO;
import com.alnt.policyengine.service.MitigationControlService;

import play.libs.concurrent.HttpExecutionContext;

public class MitigationControlController extends BaseController<MitigationControl, MitigationControlDTO> {
	
	
		
	@Inject
	public MitigationControlController(MitigationControlService mitigationControlService, HttpExecutionContext ec) {
		super(mitigationControlService, ec, MitigationControl.class, MitigationControlDTO.class);
	}

}
