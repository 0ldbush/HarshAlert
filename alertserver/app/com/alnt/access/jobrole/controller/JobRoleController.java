package com.alnt.access.jobrole.controller;


import javax.inject.Inject;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.access.jobrole.domain.dto.JobRoleDTO;
import com.alnt.access.jobrole.service.JobRoleService;
import com.alnt.platform.base.controller.BaseController;

import play.Logger;
import play.libs.concurrent.HttpExecutionContext;

public class JobRoleController extends BaseController<JobRole, JobRoleDTO> {
	
	@SuppressWarnings("unused")
	private static Logger.ALogger log = Logger.of(JobRoleController.class );
		
	@Inject
	public JobRoleController(JobRoleService roleService, HttpExecutionContext ec) {
		super(roleService, ec, JobRole.class, JobRoleDTO.class);
	}
	
}
