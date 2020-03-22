package com.alnt.identity.user.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.identity.user.domain.IdentityUser;
import com.alnt.identity.user.domain.dto.IdentityUserDTO;
import com.alnt.identity.user.mapper.IdentityUserMapper;
import com.alnt.identity.user.repository.IdentityUserRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class IdentityUserServiceImpl extends BaseServiceImpl<IdentityUser, IdentityUserDTO> implements IdentityUserService {
    
	@Inject
	public IdentityUserServiceImpl(HttpExecutionContext ec, IdentityUserRepository repository) {
		super( ec, repository, IdentityUserMapper.INSTANCE);
	}
}
