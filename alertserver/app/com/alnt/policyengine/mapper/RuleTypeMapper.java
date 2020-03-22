package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.RuleType;
import com.alnt.policyengine.domain.dto.RuleTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RuleTypeMapper extends BaseMapper<RuleTypeDTO, RuleType> {

	public static RuleTypeMapper INSTANCE = Mappers.getMapper(RuleTypeMapper.class);

}
