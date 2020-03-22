package com.alnt.ruleengine.controller;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.domain.dto.DefaultOutput;
import com.alnt.ruleengine.service.RuleEngineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;

public class RuleEngineController extends BaseController<Rule,RuleDTO> {
	
	
	@Inject
	public RuleEngineController(RuleEngineService ruleEnginService, HttpExecutionContext ec) {
		super(ruleEnginService, ec, Rule.class, RuleDTO.class);
	}

	public CompletionStage<Result> evaluateExpr(Http.Request request) {
		
		
		final GsonBuilder gsonBuildr = new GsonBuilder();
      //  gsonBuildr.registerTypeAdapter(Date.class, new DateDeserializer());
		 Gson jsonP = gsonBuildr.create();
		 HashMap requestObject = jsonP.fromJson(Json.stringify(request.body().asJson()), HashMap.class);
		
		
		
			String expression =  requestObject.get("expr").toString();
			return ((RuleEngineService)getService()).evaluateExpr(expression)
					.thenApplyAsync(objectStream -> ok(
							Json.toJson(new ApiResponse(Boolean.TRUE, objectStream, null))
				), ec.current())
					.exceptionally(
				            t -> {
				            	return internalServerError(
										Json.toJson(new ApiResponse(Boolean.FALSE, ((Exception)t).getMessage(), null))
							);
				            });
			
		
		
		
	}
	
	public CompletionStage<Result> evaluateRule(Http.Request request) {
		
		
		final GsonBuilder gsonBuildr = new GsonBuilder();
      //  gsonBuildr.registerTypeAdapter(Date.class, new DateDeserializer());
		 Gson jsonP = gsonBuildr.create();
		 HashMap requestObject = jsonP.fromJson(Json.stringify(request.body().asJson()), HashMap.class);
		
		
		
		String ruleid =  requestObject.get("ruleid").toString();
		
		Long pg = Long.parseLong(ruleid);
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((RuleEngineService)getService()).evaluateRule(requestDetails, requestObject,pg).thenApplyAsync(
					objectStream -> ok(
								Json.toJson(new ApiResponse(Boolean.TRUE, objectStream, null))
					), ec.current());
		}, ec.current());
	}
	
	public CompletionStage<Result> postRequest(Http.Request request) {
		
		
		final GsonBuilder gsonBuildr = new GsonBuilder();
      //  gsonBuildr.registerTypeAdapter(Date.class, new DateDeserializer());
		 Gson jsonP = gsonBuildr.create();
		 HashMap requestObject = jsonP.fromJson(Json.stringify(request.body().asJson()), HashMap.class);
		
		
		
		Object policyGroup  = requestObject.get("policyGroup");
	
			
			final List<String> pg = (List<String>)policyGroup;
			
			return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
				
				long l = System.currentTimeMillis();
				CompletableFuture applyRules = ((RuleEngineService)getService()).applyRules(requestDetails, requestObject, pg);
				
				System.err.print("Done in " + (System.currentTimeMillis() - l) / 1000 + " sec"); 
				return applyRules
						.thenApplyAsync(
						objectStream -> ok(
									//Json.toJson(new ApiResponse(Boolean.TRUE, objectStream, null))
									JsonViews.toJson(new ApiResponse(Boolean.TRUE, objectStream, null),JsonViews.Header.class)
						), ec.current())
						.exceptionally(
					            t -> {
					            	return internalServerError(
											Json.toJson(new ApiResponse(Boolean.TRUE, ((Exception)t).getMessage(), null))
								);
					             // return ((Exception)t).getMessage();
					            });
				
			}, ec.current());
			
		
		

	}
}
