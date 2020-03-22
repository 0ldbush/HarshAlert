package com.alnt.platform.core.binaryresource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.binaryresource.domain.BinaryResource;
import com.alnt.platform.core.binaryresource.domain.dto.BinaryResourceDTO;

@Mapper(config = MapperCentralConfig.class)
public interface BinaryResourceMapper extends BaseMapper<BinaryResourceDTO, BinaryResource> {

	public static BinaryResourceMapper INSTANCE = Mappers.getMapper(BinaryResourceMapper.class);

}
