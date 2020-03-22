package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.PolicyType;
import com.alnt.policyengine.domain.dto.PolicyTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface PolicyTypeMapper extends BaseMapper<PolicyTypeDTO, PolicyType> {

	public static PolicyTypeMapper INSTANCE = Mappers.getMapper(PolicyTypeMapper.class);
	

}
