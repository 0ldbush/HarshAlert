package com.alnt.identity.usergroup.service;

import com.alnt.identity.usergroup.domain.IdentityUserGroup;
import com.alnt.identity.usergroup.domain.dto.IdentityUserGroupDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(IdentityUserGroupServiceImpl.class)
public interface IdentityUserGroupService extends BaseService<IdentityUserGroup, IdentityUserGroupDTO> {
	

}
