package com.alnt.access.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.access.user.domain.User;
import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface UserMapper extends BaseMapper<UserDTO, User> {

	public static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

}
