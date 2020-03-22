package com.alnt.identity.role.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.identity.role.domain.dto.IdentityRoleDTO;
import com.alnt.identity.role.mapper.IdentityRoleMapper;
import com.alnt.identity.role.repository.IdentityRoleRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class IdentityRoleServiceImpl extends BaseServiceImpl<IdentityRole, IdentityRoleDTO> implements IdentityRoleService {
    
	@Inject
	public IdentityRoleServiceImpl(HttpExecutionContext ec, IdentityRoleRepository repository) {
		super( ec, repository, IdentityRoleMapper.INSTANCE);
	}
}
