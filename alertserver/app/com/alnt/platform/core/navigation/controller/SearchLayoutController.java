package com.alnt.platform.core.navigation.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.navigation.domain.SearchLayout;
import com.alnt.platform.core.navigation.domain.dto.SearchLayoutDTO;
import com.alnt.platform.core.navigation.service.SearchLayoutService;

import play.libs.concurrent.HttpExecutionContext;

public class SearchLayoutController extends BaseController<SearchLayout, SearchLayoutDTO> {
	
	
		
	@Inject
	public SearchLayoutController(SearchLayoutService searchLayoutService, HttpExecutionContext ec) {
		super(searchLayoutService, ec, SearchLayout.class, SearchLayoutDTO.class);
	}

}
