package com.alnt.platform.core.docnumberrange.controller;


import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.core.docnumberrange.domain.DocNumberRange;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRangeDTO;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRequestDTO;
import com.alnt.platform.core.docnumberrange.service.DocNumberRangeService;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;

public class DocNumberRangeController extends BaseController<DocNumberRange, DocNumberRangeDTO> {
			
	@Inject
	public DocNumberRangeController(DocNumberRangeService docNumberRangeService, HttpExecutionContext ec) {
		super(docNumberRangeService, ec, DocNumberRange.class, DocNumberRangeDTO.class);
	}
	
	public CompletionStage<Result> getDocNumber(Http.Request request) {
		JsonNode json = request.body().asJson();
		DocNumberRequestDTO docNumberRequest = Json.fromJson(json, DocNumberRequestDTO.class);
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((DocNumberRangeService)getService()).getDocNumber(requestDetails, docNumberRequest)
					.thenApplyAsync(object -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, object.get(), null))), ec.current());
		}, ec.current());
	}

}
