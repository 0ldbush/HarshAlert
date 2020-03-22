package com.alnt.platform.core.navigation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.navigation.domain.Activity;
import com.alnt.platform.core.navigation.domain.dto.ActivityDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ActivityMapper extends BaseMapper<ActivityDTO, Activity> {

	public static ActivityMapper INSTANCE = Mappers.getMapper(ActivityMapper.class);

}
