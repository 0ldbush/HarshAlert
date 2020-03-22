package com.alnt.identity.usergroup.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.identity.usergroup.domain.IdentityUserGroup;
import com.alnt.identity.usergroup.domain.dto.IdentityUserGroupDTO;
import com.alnt.identity.usergroup.mapper.IdentityUserGroupMapper;
import com.alnt.identity.usergroup.repository.IdentityUserGroupRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class IdentityUserGroupServiceImpl extends BaseServiceImpl<IdentityUserGroup, IdentityUserGroupDTO> implements IdentityUserGroupService {
    
	@Inject
	public IdentityUserGroupServiceImpl(HttpExecutionContext ec, IdentityUserGroupRepository repository) {
		super( ec, repository, IdentityUserGroupMapper.INSTANCE);
	}
}
