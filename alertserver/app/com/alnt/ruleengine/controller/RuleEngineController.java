package com.alnt.ruleengine.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Inject;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.alnt.platform.application.logger.LoggerHelper;
import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.application.logger.service.Logger;
import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.business.JSONManipulator;
import com.alnt.ruleengine.service.RuleEngineService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;

public class RuleEngineController extends BaseController<Rule,RuleDTO> {
	
	
    Logger log;
	
	@Inject
	public RuleEngineController(RuleEngineService ruleEnginService, HttpExecutionContext ec,LoggerHelper loggerHelper) {
		super(ruleEnginService, ec, Rule.class, RuleDTO.class);
		this.log=loggerHelper.getLogger(RuleEngineController.class);
	}

	@Inject
	private JSONManipulator man;

	public CompletionStage<Result> checkDeletion(Http.Request request) {
		
		
		final GsonBuilder gsonBuildr = new GsonBuilder();
      //  gsonBuildr.registerTypeAdapter(Date.class, new DateDeserializer());
		 Gson jsonP = gsonBuildr.create();
		 HashMap requestObject = jsonP.fromJson(Json.stringify(request.body().asJson()), HashMap.class);
		
		 CompletionStage<RequestDetails> fetchRequestDetails = fetchRequestDetails(request);
		 
		 CompletionStage<Map> jsonModified = fetchRequestDetails.thenApplyAsync(requestDetails -> {
				
				String modifiedJson = man.applyConfigToJSON(requestDetails, Json.stringify(request.body().asJson()));
				HashMap jsonObject = jsonP.fromJson(modifiedJson, HashMap.class);
				
				Map m = new HashMap();
				//m.put("request", requestDetails);
				m.put("json", jsonObject);
				
				return m;
			});
		 
		
			return jsonModified
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
		 String stringify = Json.stringify(request.body().asJson());
		 
		 
		 
		//HashMap requestObject = jsonP.fromJson(stringify, HashMap.class);
		
		
		 CompletionStage<RequestDetails> fetchRequestDetails = fetchRequestDetails(request);
		
		 CompletionStage<Map> jsonModified = fetchRequestDetails.thenApplyAsync(requestDetails -> {
				String modifiedJson = man.applyConfigToJSON(requestDetails, stringify);
				HashMap jsonObject = jsonP.fromJson(modifiedJson, HashMap.class);
				
				Map m = new ConcurrentHashMap<>();
				m.put("request", requestDetails);
				m.put("json", jsonObject);
				
				return m;
			});
		 
		 return jsonModified.thenComposeAsync(ob -> {
				
				RequestDetails requestDetails = (RequestDetails) ob.get("request");
				Map requestObject =  (Map) ob.get("json");
				long l = System.currentTimeMillis();
				
				final List<String> pg  = (List<String>) requestObject.get("policyGroup");
				CompletableFuture applyRules = ((RuleEngineService)getService()).applyRules(requestDetails, requestObject, pg);
		
//				System.err.print("Done in " + (System.currentTimeMillis() - l) / 1000 + " sec"); 
				return applyRules
				.thenApplyAsync(
				objectStream -> ok(
							//Json.toJson(new ApiResponse(Boolean.TRUE, objectStream, null))
							JsonViews.toJson(new ApiResponse(Boolean.TRUE, objectStream, null),JsonViews.Header.class)
				), ec.current())
				.exceptionally(
			            t -> {
			            	System.err.print(ExceptionUtils.getStackTrace((Exception)t));
			            	return internalServerError(
									Json.toJson(new ApiResponse(Boolean.TRUE, ((Exception)t).getMessage(), null))
						);
			             // return ((Exception)t).getMessage();
			            });
		
		 }, ec.current());		 
	
			

	}
}