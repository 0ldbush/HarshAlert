package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.RiskType;
import com.alnt.policyengine.domain.dto.RiskTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RiskTypeMapper extends BaseMapper<RiskTypeDTO, RiskType> {

	public static RiskTypeMapper INSTANCE = Mappers.getMapper(RiskTypeMapper.class);

}
