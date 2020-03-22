package com.alnt.platform.core.navigation.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MenuItemMapper extends BaseMapper<MenuItemDTO, MenuItem> {

	public static MenuItemMapper INSTANCE = Mappers.getMapper(MenuItemMapper.class);


}
