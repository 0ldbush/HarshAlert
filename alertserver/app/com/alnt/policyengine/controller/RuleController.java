package com.alnt.policyengine.controller;


import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.policyengine.domain.dto.UploadRuleDTO;
import com.alnt.policyengine.service.RuleService;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http.Request;
import play.mvc.Result;

public class RuleController extends BaseController<Rule, RuleDTO> {
	@Inject
	public RuleController(RuleService ruleService, HttpExecutionContext ec) {
		super(ruleService, ec, Rule.class, RuleDTO.class);
	}
	
	public CompletionStage<Result> uploadExcel(Request request)
	{
		JsonNode json = request.body().asJson();
		final UploadRuleDTO uploadRuleDTO = Json.fromJson(json, UploadRuleDTO.class);	
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((RuleService)this.getService()).uploadExcel(requestDetails,uploadRuleDTO).thenApplyAsync(response->{
				return	ok(Json.toJson(response));
			}, ec.current());
			
		},ec.current());
	}

}
