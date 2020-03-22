package com.alnt.identity.usergroup.controller;

import javax.inject.Inject;

import com.alnt.identity.usergroup.domain.IdentityUserGroup;
import com.alnt.identity.usergroup.domain.dto.IdentityUserGroupDTO;
import com.alnt.identity.usergroup.service.IdentityUserGroupService;
import com.alnt.platform.base.controller.ExternalBaseController;

import play.Logger;
import play.libs.concurrent.HttpExecutionContext;

public class IdentityUserGroupController extends ExternalBaseController<IdentityUserGroup, IdentityUserGroupDTO> {
	
	@SuppressWarnings("unused")
	private static Logger.ALogger log = Logger.of(IdentityUserGroupController.class );
		
	@Inject
	public IdentityUserGroupController(IdentityUserGroupService IdentityUserGroupService, HttpExecutionContext ec) {
				super(IdentityUserGroupService, ec, IdentityUserGroup.class, IdentityUserGroupDTO.class,"IDENTITYSOURCE");
	}
	
}

