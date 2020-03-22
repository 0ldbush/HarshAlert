package com.alnt.identity.role.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.identity.role.domain.dto.IdentityRoleDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface IdentityRoleMapper extends BaseMapper<IdentityRoleDTO, IdentityRole> {

	public static IdentityRoleMapper INSTANCE = Mappers.getMapper(IdentityRoleMapper.class);
	


}
