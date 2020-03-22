package com.alnt.platform.core.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import com.alnt.access.user.service.UserService;
import com.alnt.platform.application.security.jwt.Attrs;
import com.alnt.platform.application.security.jwt.VerifiedJwt;
import com.alnt.platform.base.persistence.idgenerator.SequenceIDProvider;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiResponse;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class UtilApisController extends Controller {

	@Inject
	protected HttpExecutionContext ec;
	@Inject
	protected UserService userService;
	
	protected CompletionStage<RequestDetails> fetchRequestDetails(Http.Request request) {
		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setHttpMethod(request.method());
		requestDetails.setUrlPath(request.path());
		VerifiedJwt jwt = request.attrs().containsKey(Attrs.VERIFIED_JWT) ? request.attrs().get(Attrs.VERIFIED_JWT) : null; // TODO: handled null pointer for time being , need to
																															// remove this
		if (jwt != null) {
			requestDetails.setTenantName(jwt.getTenant());
			if (jwt != null && jwt.getUserId() != null) {
				return userService.get(requestDetails, jwt.getUserId()).thenApplyAsync(userO -> {
					if (userO.isPresent()) {
						requestDetails.setUser(userO.get());
					}
					return requestDetails;
				});
			}
		}
		return CompletableFuture.supplyAsync(() -> {
			return null;
		});
	}
	public CompletionStage<Result> getNewIDs(Http.Request request) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			List<String> ids = new ArrayList<>();
			SequenceIDProvider prov = SequenceIDProvider.getInstance();
			for(int i=0; i<50; i++) {
				ids.add(""+prov.nextId());
			}
			return CompletableFuture.supplyAsync(() -> {
				System.out.println(Json.toJson(new ApiResponse(Boolean.TRUE, ids, null)));
					return ok(Json.toJson(new ApiResponse(Boolean.TRUE, ids, null)));
			});
		}, ec.current());
	}
}
