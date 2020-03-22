package com.alnt.policyengine.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.MitigationControlType;
import com.alnt.policyengine.domain.dto.MitigationControlTypeDTO;
import com.alnt.policyengine.service.MitigationControlTypeService;

import play.libs.concurrent.HttpExecutionContext;

public class MitigationControlTypeController extends BaseController<MitigationControlType, MitigationControlTypeDTO> {

	@Inject
	public MitigationControlTypeController(MitigationControlTypeService mitigationControlTypeService,
			HttpExecutionContext ec) {
		super(mitigationControlTypeService, ec, MitigationControlType.class, MitigationControlTypeDTO.class);
	}

}
