package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.ResponseCodeType;
import com.alnt.policyengine.domain.dto.ResponseCodeTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ResponseCodeTypeMapper extends BaseMapper<ResponseCodeTypeDTO, ResponseCodeType> {

	public static ResponseCodeTypeMapper INSTANCE = Mappers.getMapper(ResponseCodeTypeMapper.class);

}
