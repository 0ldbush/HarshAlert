package com.alnt.platform.core.navigation.service;

import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.alnt.platform.core.navigation.mapper.MenuItemMapper;
import com.alnt.platform.core.navigation.repository.MenuItemRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MenuServiceImpl extends BaseServiceImpl<MenuItem, MenuItemDTO> implements MenuService {
    
	@Inject
	public MenuServiceImpl(HttpExecutionContext ec, MenuItemRepository repository) {
		super( ec, repository, MenuItemMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Stream<MenuItemDTO>> getMenuForUser(RequestDetails requestDetails, SearchCriteria searchCriteria) {
		return ((MenuItemRepository)this.getDaoRepository()).getMenuForUser(requestDetails, searchCriteria.getUserId()).thenApplyAsync(dataStream -> {
            return dataStream.map(entity -> getMapper().entityToDTO(entity));
        }, ec.current());
	}

}
