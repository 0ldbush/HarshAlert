package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;

@Mapper(config = MapperCentralConfig.class)
public interface PolicyMapper extends BaseMapper<PolicyDTO, Policy> {

	public static PolicyMapper INSTANCE = Mappers.getMapper(PolicyMapper.class);

}
