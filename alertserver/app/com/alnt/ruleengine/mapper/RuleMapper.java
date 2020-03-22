package com.alnt.ruleengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;

@Mapper(config = MapperCentralConfig.class)
public interface RuleMapper extends BaseMapper<RuleDTO, Rule> {

	public static RuleMapper INSTANCE = Mappers.getMapper(RuleMapper.class);

}
