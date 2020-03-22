package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.MitigationControl;
import com.alnt.policyengine.domain.dto.MitigationControlDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MitigationControlMapper extends BaseMapper<MitigationControlDTO, MitigationControl> {

	public static MitigationControlMapper INSTANCE = Mappers.getMapper(MitigationControlMapper.class);

}
