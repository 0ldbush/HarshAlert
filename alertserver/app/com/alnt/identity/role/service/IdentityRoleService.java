package com.alnt.identity.role.service;

import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.identity.role.domain.dto.IdentityRoleDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(IdentityRoleServiceImpl.class)
public interface IdentityRoleService extends BaseService<IdentityRole, IdentityRoleDTO> {
	

}
