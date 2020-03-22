package com.alnt.identity.user.controller;

import javax.inject.Inject;

import com.alnt.identity.user.domain.IdentityUser;
import com.alnt.identity.user.domain.dto.IdentityUserDTO;
import com.alnt.identity.user.service.IdentityUserService;
import com.alnt.platform.base.controller.ExternalBaseController;

import play.Logger;
import play.libs.concurrent.HttpExecutionContext;

public class IdentityUserController extends ExternalBaseController<IdentityUser, IdentityUserDTO> {
	
	@SuppressWarnings("unused")
	private static Logger.ALogger log = Logger.of(IdentityUserController.class );
		
	@Inject
	public IdentityUserController(IdentityUserService identityUserService, HttpExecutionContext ec) {
				super(identityUserService, ec, IdentityUser.class, IdentityUserDTO.class,"IDENTITYSOURCE");
	}
	
}

