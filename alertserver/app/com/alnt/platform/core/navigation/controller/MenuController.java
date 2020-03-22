package com.alnt.platform.core.navigation.controller;


import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.core.navigation.domain.MenuItem;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.alnt.platform.core.navigation.service.MenuService;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;

public class MenuController extends BaseController<MenuItem, MenuItemDTO> {
	
	
		
	@Inject
	public MenuController(MenuService menuService, HttpExecutionContext ec) {
		super(menuService, ec, MenuItem.class, MenuItemDTO.class);
	}
	
	public CompletionStage<Result> getMenuForUser(Http.Request request) {
		JsonNode json = request.body().asJson();
		SearchCriteria searchCriteria = Json.fromJson(json, SearchCriteria.class);		
		return ((MenuService)getService()).getMenuForUser(new RequestDetails(), searchCriteria).thenApplyAsync(
					objectStream -> ok(
								Json.toJson(new ApiResponse(Boolean.TRUE, objectStream.collect(Collectors.toList()), null))
					), ec.current());
	}

}
