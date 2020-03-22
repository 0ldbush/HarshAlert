package com.alnt.platform.core.navigation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.navigation.domain.SearchLayout;
import com.alnt.platform.core.navigation.domain.dto.SearchLayoutDTO;

@Mapper(config = MapperCentralConfig.class)
public interface SearchLayoutMapper extends BaseMapper<SearchLayoutDTO, SearchLayout> {

	public static SearchLayoutMapper INSTANCE = Mappers.getMapper(SearchLayoutMapper.class);

}
