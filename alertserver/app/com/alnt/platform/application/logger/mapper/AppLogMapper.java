package com.alnt.platform.application.logger.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.application.logger.domain.AppLog;
import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface AppLogMapper extends BaseMapper<AppLogDTO, AppLog> {

	public static AppLogMapper INSTANCE = Mappers.getMapper(AppLogMapper.class);

}
