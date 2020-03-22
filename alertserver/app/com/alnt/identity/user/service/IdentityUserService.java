package com.alnt.identity.user.service;

import com.alnt.identity.user.domain.IdentityUser;
import com.alnt.identity.user.domain.dto.IdentityUserDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(IdentityUserServiceImpl.class)
public interface IdentityUserService extends BaseService<IdentityUser, IdentityUserDTO> {
	

}
