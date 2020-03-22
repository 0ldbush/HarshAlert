package com.alnt.platform.base.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import com.alnt.platform.application.security.jwt.Attrs;
import com.alnt.platform.application.security.jwt.VerifiedJwt;
import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseService;
import com.fasterxml.jackson.databind.JsonNode;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public abstract class ExternalBaseController<E extends Entity, D extends DTO> extends BaseController {
	private String source;
	public ExternalBaseController(BaseService<E, D> service, HttpExecutionContext ec, Class<E> domainClass, Class<D> dtoClass,String source) {
		super(service,ec,domainClass,dtoClass);
		this.source = source;
		
	}
	protected CompletionStage<RequestDetails> fetchRequestDetails(Http.Request request) {
		Config conf = ConfigFactory.load();
		String externaltenant  = conf.getString(source);

		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setHttpMethod(request.method());
		requestDetails.setUrlPath(request.path());
		VerifiedJwt jwt = request.attrs().get(Attrs.VERIFIED_JWT);
		requestDetails.setTenantName(jwt.getTenant());
		if(jwt != null && jwt.getUserId() != null) {
			return userService.get(requestDetails, jwt.getUserId()).thenApplyAsync(userO -> {
				if(userO.isPresent()) {
					requestDetails.setUser(userO.get());
				}
				requestDetails.setTenantName(externaltenant);
				return requestDetails;
			});
		}
		return CompletableFuture.supplyAsync(() ->  { return null ;} );
	}


	

}
