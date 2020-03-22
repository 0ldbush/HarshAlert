package com.alnt.platform.core.navigation.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.navigation.domain.SearchLayout;
import com.alnt.platform.core.navigation.domain.dto.SearchLayoutDTO;
import com.alnt.platform.core.navigation.mapper.SearchLayoutMapper;
import com.alnt.platform.core.navigation.repository.SearchLayoutRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class SearchLayoutServiceImpl extends BaseServiceImpl<SearchLayout, SearchLayoutDTO> implements SearchLayoutService {
    
	@Inject
	public SearchLayoutServiceImpl(HttpExecutionContext ec, SearchLayoutRepository repository) {
		super( ec, repository, SearchLayoutMapper.INSTANCE);
	}	

}
