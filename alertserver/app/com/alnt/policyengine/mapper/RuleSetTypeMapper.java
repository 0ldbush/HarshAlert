package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.RuleSetType;
import com.alnt.policyengine.domain.dto.RuleSetTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RuleSetTypeMapper extends BaseMapper<RuleSetTypeDTO, RuleSetType> {

	public static RuleSetTypeMapper INSTANCE = Mappers.getMapper(RuleSetTypeMapper.class);

}
