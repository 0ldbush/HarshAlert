package com.alnt.platform.core.classdef.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ClassDefMapper extends BaseMapper<ClassDefDTO, ClassDef> {

	public static ClassDefMapper INSTANCE = Mappers.getMapper(ClassDefMapper.class);


}
