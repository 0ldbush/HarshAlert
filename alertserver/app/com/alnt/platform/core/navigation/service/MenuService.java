package com.alnt.platform.core.navigation.service;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MenuServiceImpl.class)
public interface MenuService extends BaseService<MenuItem, MenuItemDTO> {
	
	CompletionStage<Stream<MenuItemDTO>> getMenuForUser(RequestDetails requestDetails, SearchCriteria searchCriteria);

}
