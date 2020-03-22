package com.alnt.platform.core.lists.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.lists.domain.Lists;
import com.alnt.platform.core.lists.domain.dto.ListsDTO;
import com.alnt.platform.core.lists.mapper.ListsMapper;
import com.alnt.platform.core.lists.repository.ListsRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ListsServiceImpl extends BaseServiceImpl<Lists, ListsDTO> implements ListsService {
    
	@Inject
	public ListsServiceImpl(HttpExecutionContext ec, ListsRepository repository) {
		super( ec, repository, ListsMapper.INSTANCE);
	}

}
