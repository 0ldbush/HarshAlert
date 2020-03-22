package com.alnt.platform.core.navigation.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MenuItemServiceImpl.class)
public interface MenuItemService extends BaseService<MenuItem, MenuItemDTO> {

}
