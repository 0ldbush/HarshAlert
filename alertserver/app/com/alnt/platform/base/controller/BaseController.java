package com.alnt.platform.base.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alnt.access.user.service.UserService;
import com.alnt.platform.application.security.jwt.Attrs;
import com.alnt.platform.application.security.jwt.VerifiedJwt;
import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.request.SortBy;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseService;
import com.fasterxml.jackson.databind.JsonNode;

import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

public abstract class BaseController<E extends Entity, D extends DTO> extends Controller {

	@SuppressWarnings("unused")
	private static final Log LOG = LogFactory.getLog(BaseController.class);

	protected final HttpExecutionContext ec;
	
	@Inject
//	@Named(PlatformModule.LOCAL_CACHE)
	protected UserService userService;

	private final BaseService<E, D> service;
	private final Class<E> domainClass;
	private final Class<D> dtoClass;

	public BaseController(BaseService<E, D> service, HttpExecutionContext ec, Class<E> domainClass, Class<D> dtoClass) {
		super();
		this.ec = ec;
		this.service = service;
		this.domainClass = domainClass;
		this.dtoClass = dtoClass;
	}

	public BaseService<E, D> getService() {
		return service;
	}

	public Class<E> getDomainClass() {
		return domainClass;
	}

	public Class<D> getDTOClass() {
		return dtoClass;
	}

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

	public CompletionStage<Result> list(Http.Request request) {
		JsonNode json = request.body().asJson();
		SearchCriteria searchCriteria = Json.fromJson(json, SearchCriteria.class);
		if ((searchCriteria.getSortBy() == null || searchCriteria.getSortBy().isEmpty()) && StringUtils.isNotBlank(searchCriteria.getSortByString())) {
			JsonNode parse = Json.parse(searchCriteria.getSortByString());
			if (parse.isArray()) {
				Iterator<JsonNode> elements = parse.elements();
				while (elements.hasNext()) {
					searchCriteria.getSortBy().add(Json.fromJson(elements.next(), SortBy.class));
				}
			}
		}
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().find(requestDetails, searchCriteria)
					.thenApplyAsync(objectStream -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, objectStream.collect(Collectors.toList()), null))), ec.current());
		}, ec.current());
	}

	public CompletionStage<Result> find(Http.Request request) {
		JsonNode json = request.body().asJson();
		SearchCriteria searchCriteria = Json.fromJson(json, SearchCriteria.class);
		if ((searchCriteria.getSortBy() == null || searchCriteria.getSortBy().isEmpty()) && StringUtils.isNotBlank(searchCriteria.getSortByString())) {
			JsonNode parse = Json.parse(searchCriteria.getSortByString());
			if (parse.isArray()) {
				Iterator<JsonNode> elements = parse.elements();
				while (elements.hasNext()) {
					searchCriteria.getSortBy().add(Json.fromJson(elements.next(), SortBy.class));
				}
			}
		}
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().findAll(requestDetails, searchCriteria).thenApplyAsync(object -> ok(Json.toJson(object)), ec.current());
		}, ec.current());
	}

	public CompletionStage<Result> get(Http.Request request, Long id) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().get(requestDetails, id).thenApplyAsync(optionalResource -> {
				return optionalResource.map(resource -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, resource, null)))).orElseGet(Results::notFound);
			}, ec.current());
		}, ec.current());
	}
	
	public CompletionStage<Result> getByExtId(Http.Request request, String extId) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().getBy(requestDetails,"extId", extId)
					.thenApplyAsync(objectStream -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, objectStream.collect(Collectors.toList()), null))), ec.current());
		}, ec.current());
	}

	public CompletionStage<Result> update(Http.Request request, Long id) {
		JsonNode json = request.body().asJson();
		D resource = Json.fromJson(json, getDTOClass());
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().save(requestDetails, resource).thenApplyAsync(optionalResource -> {
				return optionalResource.map(r -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, r, null)))).orElseGet(Results::notFound);
			}, ec.current());
		}, ec.current());
	}

	public CompletionStage<Result> save(Http.Request request) {
		JsonNode json = request.body().asJson();
		if(json.isArray())
		{
			List<D> resources=new ArrayList<>();
			int length=json.size();
			for(int i=0;i<length;i++)
			{
				resources.add( Json.fromJson(json.get(i), getDTOClass()));
			}
			return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
				return getService().saveAll(requestDetails, resources).thenApplyAsync(optionalResource -> {
				List<D> results=	optionalResource.stream().filter(optional->optional.isPresent()). map(data->data.get()).collect(Collectors.toList());
					return ok(Json.toJson(new ApiResponse(Boolean.TRUE, results, null)));
				}, ec.current());
			}, ec.current());
		}else
		{
		D resource = Json.fromJson(json, getDTOClass());
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().save(requestDetails, resource).thenApplyAsync(optionalResource -> {
				return optionalResource.map(saveddata -> ok(Json.toJson(new ApiResponse(Boolean.TRUE, saveddata, null)))).orElseGet(Results::notFound);
			}, ec.current());
		}, ec.current());
		}
	}

//	public CompletionStage<Result> create(Http.Request request) {
//		JsonNode json = request.body().asJson();
//		final D resource = Json.fromJson(json, getDTOClass());
//		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
//			return getService().create(requestDetails, resource).thenApplyAsync(savedResource -> {
//				return created(Json.toJson(new ApiResponse(Boolean.TRUE, savedResource, null)));
//			}, ec.current());
//		}, ec.current());
//	}
	
	public CompletionStage<Result> delete(Http.Request request, Long id) {
		return fetchRequestDetails(request).thenComposeAsync(requestDetails -> {
			return getService().delete(requestDetails, id).thenApplyAsync(data -> {
			ApiMessage apiMessage=	new ApiMessage(data, ApiMessageType.INFO);
			List<ApiMessage>  messageList=new ArrayList<ApiMessage>();
			messageList.add(apiMessage);
				return ok(Json.toJson(new ApiResponse(Boolean.TRUE, null,messageList )));
			}, ec.current());
		}, ec.current());
	}

}
