package com.alnt.platform.core.lists.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.lists.domain.Lists;
import com.alnt.platform.core.lists.domain.dto.ListsDTO;
import com.alnt.platform.core.lists.service.ListsService;

import play.libs.concurrent.HttpExecutionContext;

public class ListsController extends BaseController<Lists, ListsDTO> {
			
	@Inject
	public ListsController(ListsService listsService, HttpExecutionContext ec) {
		super(listsService, ec, Lists.class, ListsDTO.class);
	}

}
