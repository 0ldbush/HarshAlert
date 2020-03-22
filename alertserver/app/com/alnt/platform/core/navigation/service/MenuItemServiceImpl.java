package com.alnt.platform.core.navigation.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.alnt.platform.core.navigation.mapper.MenuItemMapper;
import com.alnt.platform.core.navigation.repository.MenuItemRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MenuItemServiceImpl extends BaseServiceImpl<MenuItem, MenuItemDTO> implements MenuItemService {
    
	@Inject
	public MenuItemServiceImpl(HttpExecutionContext ec, MenuItemRepository repository) {
		super( ec, repository, MenuItemMapper.INSTANCE);
	}
	

	

}
