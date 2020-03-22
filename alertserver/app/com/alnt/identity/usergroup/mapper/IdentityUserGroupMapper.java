package com.alnt.identity.usergroup.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.identity.usergroup.domain.IdentityUserGroup;
import com.alnt.identity.usergroup.domain.dto.IdentityUserGroupDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface IdentityUserGroupMapper extends BaseMapper<IdentityUserGroupDTO, IdentityUserGroup> {

	public static IdentityUserGroupMapper INSTANCE = Mappers.getMapper(IdentityUserGroupMapper.class);
	
}
