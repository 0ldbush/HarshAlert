package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.PolicyGroup;
import com.alnt.policyengine.domain.dto.PolicyGroupDTO;

@Mapper(config = MapperCentralConfig.class)
public interface PolicyGroupMapper extends BaseMapper<PolicyGroupDTO, PolicyGroup> {

	public static PolicyGroupMapper INSTANCE = Mappers.getMapper(PolicyGroupMapper.class);


}
