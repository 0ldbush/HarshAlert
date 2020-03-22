package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.Risk;
import com.alnt.policyengine.domain.dto.RiskDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RiskMapper extends BaseMapper<RiskDTO, Risk> {

	public static RiskMapper INSTANCE = Mappers.getMapper(RiskMapper.class);

}
