package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.ResponseCode;
import com.alnt.policyengine.domain.dto.ResponseCodeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ResponseCodeMapper extends BaseMapper<ResponseCodeDTO, ResponseCode> {

	public static ResponseCodeMapper INSTANCE = Mappers.getMapper(ResponseCodeMapper.class);

}
