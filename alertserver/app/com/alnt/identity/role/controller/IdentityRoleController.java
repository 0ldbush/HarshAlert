package com.alnt.identity.role.controller;

import javax.inject.Inject;

import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.identity.role.domain.dto.IdentityRoleDTO;
import com.alnt.identity.role.service.IdentityRoleService;
import com.alnt.platform.base.controller.ExternalBaseController;

import play.Logger;
import play.libs.concurrent.HttpExecutionContext;

public class IdentityRoleController extends ExternalBaseController<IdentityRole, IdentityRoleDTO> {
	
	@SuppressWarnings("unused")
	private static Logger.ALogger log = Logger.of(IdentityRoleController.class );
		
	@Inject
	public IdentityRoleController(IdentityRoleService identityRoleService, HttpExecutionContext ec) {
				super(identityRoleService, ec, IdentityRole.class, IdentityRoleDTO.class,"IDENTITYSOURCE");
	}
	
}

