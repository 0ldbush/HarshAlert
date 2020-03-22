package com.alnt.platform.core.apiservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.apiservice.domain.ApiService;
import com.alnt.platform.core.apiservice.domain.dto.ApiServiceDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ApiServiceMapper extends BaseMapper<ApiServiceDTO, ApiService> {

	public static ApiServiceMapper INSTANCE = Mappers.getMapper(ApiServiceMapper.class);


}
