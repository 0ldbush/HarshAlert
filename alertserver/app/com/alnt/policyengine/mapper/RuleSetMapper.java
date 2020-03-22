package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.RuleSet;
import com.alnt.policyengine.domain.dto.RuleSetDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RuleSetMapper extends BaseMapper<RuleSetDTO, RuleSet> {

	public static RuleSetMapper INSTANCE = Mappers.getMapper(RuleSetMapper.class);

}
