package com.alnt.platform.core.classdef.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.google.inject.name.Named;

import play.libs.concurrent.HttpExecutionContext;

public class ClassDefController extends BaseController<ClassDef, ClassDefDTO> {
			
	@Inject
	public ClassDefController(@Named("base") ClassDefService classDefService, HttpExecutionContext ec) {
		super(classDefService, ec, ClassDef.class, ClassDefDTO.class);
	}

}
