package com.alnt.access.user.controller;


import com.alnt.access.user.domain.User;
import com.alnt.access.user.domain.dto.PasswordChangeDTO;
import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.access.user.service.UserService;
import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.base.response.ApiResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import play.Logger;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.List;
import java.util.concurrent.CompletionStage;

public class UserController extends BaseController<User, UserDTO> {

	@SuppressWarnings("unused")
	private static Logger.ALogger log = Logger.of(UserController.class);

	@Inject
	public UserController(UserService userService, HttpExecutionContext ec) {
		super(userService, ec, User.class, UserDTO.class);
	}

	public CompletionStage<Result> getUserByName(Http.Request request) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
		return ((UserService) getService()).getUserByName(requestDetails,requestDetails.getUser().getUserName()).thenApplyAsync(optionalResource -> {
			return optionalResource.map(resource -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, resource, null))))
					.orElseGet(Results::notFound);
		}, ec.current());
		},ec.current());
	}

	public CompletionStage<Result> changePassword(Http.Request request) {
		final PasswordChangeDTO passwordChange = Json.fromJson(request.body().asJson(), PasswordChangeDTO.class);
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((UserService) getService()).changePassword(requestDetails, passwordChange)
					.thenApplyAsync(response -> {
						return ok(Json.toJson(response));
					}, ec.current());
		}, ec.current());

	}


	public CompletionStage<Result> approveResetPasswordRequest(Http.Request request) throws JsonProcessingException {
		final List<UserDTO> userDTOs = Json.mapper().readValue(request.body().asJson().toString(),
				new TypeReference<List<UserDTO>>() {});

		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return ((UserService) getService()).approveResetPasswordRequest(requestDetails,userDTOs)
					.thenApplyAsync(response -> {
						return ok(Json.toJson(response));
					}, ec.current());
		},ec.current());
	}
}
