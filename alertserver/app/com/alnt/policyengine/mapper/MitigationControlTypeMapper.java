package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.MitigationControlType;
import com.alnt.policyengine.domain.dto.MitigationControlTypeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MitigationControlTypeMapper extends BaseMapper<MitigationControlTypeDTO, MitigationControlType> {

	public static MitigationControlTypeMapper INSTANCE = Mappers.getMapper(MitigationControlTypeMapper.class);

}
