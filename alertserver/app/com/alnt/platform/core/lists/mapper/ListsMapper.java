package com.alnt.platform.core.lists.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.lists.domain.Lists;
import com.alnt.platform.core.lists.domain.dto.ListsDTO;

@Mapper(config = MapperCentralConfig.class)
public interface ListsMapper extends BaseMapper<ListsDTO, Lists> {

	public static ListsMapper INSTANCE = Mappers.getMapper(ListsMapper.class);


}
