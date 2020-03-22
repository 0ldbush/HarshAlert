package com.alnt.identity.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alnt.identity.user.domain.IdentityUser;
import com.alnt.identity.user.domain.dto.IdentityUserDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface IdentityUserMapper extends BaseMapper<IdentityUserDTO, IdentityUser> {

	public static IdentityUserMapper INSTANCE = Mappers.getMapper(IdentityUserMapper.class);
	
	@Mapping(expression = "java(entity.getText() + \" \" + entity.getLastName()!=null?entity.getLastName():\"\")", target = "text")
	public IdentityUserDTO entityToDTO(IdentityUser entity);


}
