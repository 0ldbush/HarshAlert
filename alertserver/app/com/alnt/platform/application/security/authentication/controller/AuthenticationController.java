package com.alnt.platform.application.security.authentication.controller;

import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.access.user.service.UserService;
import com.alnt.platform.application.security.hashing.HashUtils;
import com.alnt.platform.application.security.jwt.JwtValidator;
import com.alnt.platform.application.security.jwt.VerifiedJwt;
import com.alnt.platform.base.exception.BaseBusinessException;
import com.alnt.platform.base.exception.type.ErrorType;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.core.navigation.domain.dto.MenuItemDTO;
import com.alnt.platform.core.navigation.service.MenuService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.typesafe.config.Config;
import play.Logger;
import play.filters.csrf.AddCSRFToken;
import play.libs.F;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class AuthenticationController extends Controller {

	 private static Logger.ALogger log = Logger.of(AuthenticationController.class );

	private static final String BEARER = "Bearer ";
	private static final int EXPIRY_MINUTES = 10000;

	private HttpExecutionContext ec;
	private final Config config;
	private JwtValidator jwtValidator;
	private MenuService menuService;
	private UserService userService;
	
	private static final String IPADDRESS_PATTERN = 
			"^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
			"([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	@Inject
	public AuthenticationController(HttpExecutionContext ec, Config config, JwtValidator jwtValidator,
			MenuService menuService, UserService userService) {
		this.ec = ec;
		this.config = config;
		this.jwtValidator = jwtValidator;
		this.menuService = menuService;
		this.userService = userService;
	}

	@AddCSRFToken
	public CompletionStage<Result> login(Http.Request request) throws UnsupportedEncodingException {
		RequestDetails requestDetails = new RequestDetails();
		String tenant = getTenant(request);
		List<String> creds = getCredentials(request);
		Optional<String> grantType = request.queryString("grant_type");
		String username = null;
		String password = null;
		if(creds.size() > 0) {
			username = creds.get(0);
			password = creds.get(1);
			requestDetails.setTenantName(tenant);
			final UserDTO user = getUser(requestDetails, username);	
			if(user == null) {
				throw new BaseBusinessException(ErrorType.USER_NOT_FOUND);
			}
			boolean validPassword = HashUtils.verify(password, user.getPassword());
			if(!validPassword) {
				//log.error("Password Supplied Not Valid ["+password+"] password hash ["+user.getPassword()+"] new password ["+HashUtils.hash(password)+"]");
				//return supplyAsync(() -> forbidden(Json.toJson(new ApiResponse(Boolean.FALSE, null, null))), ec.current());
				throw new BaseBusinessException(ErrorType.LOGIN_FAILED);
			}
			if(grantType.isPresent() && grantType.get().equals("api_client_credential")) {
				return getOauth2Token(requestDetails, tenant, user);
			}else {
				return getToken(requestDetails, tenant, user);
			}
		} else {
			throw new BaseBusinessException(ErrorType.LOGIN_FAILED);
		}
	}
	
	private String getTenant(Http.Request request) {
		JsonNode body = request.body().asJson();
		String tenant = null;
		if(body!= null && body.hasNonNull("tenant"))
			tenant = body.get("tenant").asText();
		if(tenant  == null) {
			String host = request.host();
			if(host.indexOf("localhost") > -1) {
				tenant = "alert";
			}else if(host.indexOf(":") > -1) {
				tenant = "alert";
			}else if (isHostIpAddress(host)) {
				tenant = "alert";
			}else{
				tenant = host.substring(0, host.indexOf("."));
			}
		}
		return tenant;
	}
	
	private List<String> getCredentials(Http.Request request){
		List<String> creds = new ArrayList<String>();
		JsonNode body = request.body().asJson();

		String username = null;
		String password = null;
		if (body!= null && body.hasNonNull("username") && body.hasNonNull("password")) {
			username = body.get("username").asText();
			password = body.get("password").asText();
			creds.add(username);
			creds.add(password);
		}else {
			
			if(request.hasHeader(AUTHORIZATION)) {
				Optional<String> auth = request.header(AUTHORIZATION);
				String authHeader = null;
				if(auth.isPresent())
					authHeader = auth.get(); 
				if (authHeader != null && authHeader.toLowerCase().startsWith("basic")) {
				    // Authorization: Basic base64credentials
				    String base64Credentials = authHeader.substring("Basic".length()).trim();
				    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
				    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
				    // credentials = username:password
				    final String[] values = credentials.split(":", 2);
				    creds = Arrays.asList(values); 
				}
			}
		}
		return creds;
	}

	public Result refreshToken(Http.Request request) throws UnsupportedEncodingException {
		ObjectNode result = Json.newObject();
		result.put("success", true);
		return ok(result).withHeader(AUTHORIZATION, BEARER + getSignedToken(7l, "",""));
	}

	public CompletionStage<Result> checkToken(Http.Request request) throws UnsupportedEncodingException {

		Optional<String> authHeader = request.header(Http.HeaderNames.AUTHORIZATION);

		if (!authHeader.filter(ah -> ah.contains(BEARER)).isPresent()) {
			return supplyAsync(() -> forbidden(Json.toJson(new ApiResponse(Boolean.FALSE, null, null))), ec.current());
		}

		String token = authHeader.map(ah -> ah.replace(BEARER, "")).orElse("");
		F.Either<JwtValidator.Error, VerifiedJwt> res = jwtValidator.verify(token);

		if (res.left.isPresent()) {
			return supplyAsync(() -> forbidden(Json.toJson(new ApiResponse(Boolean.FALSE, null, null))));
		}

		if (res.right.isPresent()) {
			VerifiedJwt verifiedtoken = res.right.get();
			String username = verifiedtoken.getUsername();
			String tenant = verifiedtoken.getTenant();
			return getToken(tenant, username);
		}
		return supplyAsync(() -> forbidden(Json.toJson(new ApiResponse(Boolean.FALSE, null, null))));
	}

	private String getSignedToken(Long userId, String username, String tenant) throws UnsupportedEncodingException {
		String secret = config.getString("play.http.secret.key");

		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withIssuer("ThePlayApp").withClaim("user_id", userId).withClaim("user_name", username).withClaim("tenant", tenant)
				.withExpiresAt(Date.from(ZonedDateTime.now(ZoneId.systemDefault()).plusMinutes(EXPIRY_MINUTES).toInstant()))
				.sign(algorithm);
	}
	
	private CompletionStage<Result> getToken(String tenant, String username) {
		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setTenantName(tenant);
		final UserDTO user = getUser(requestDetails, username);
		return getToken(requestDetails, tenant, user);
	}


	private CompletionStage<Result> getToken( RequestDetails requestDetails, String tenant, UserDTO user) {			
			return supplyAsync(()-> {
				try {
					Long userId = user.getId();
					String token = getSignedToken(userId, user.getUserName(), tenant);
					Map<String, Object> data = new HashMap<String, Object>();
					List<MenuItemDTO>  navigationDtos=user.getRoles().stream().map(dd->dd.getMenuItems()).collect(Collectors.toList()).stream().flatMap(Collection::stream).collect(Collectors.toList());
					data.put("navigation_data", navigationDtos);
					data.put("user_data", user);
					data.put("access_token", token);
					return ok(Json.toJson(new ApiResponse(Boolean.TRUE, data, null))).withHeader(AUTHORIZATION,
							BEARER + token);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return forbidden();
			}, ec.current());
	}
	
	private CompletionStage<Result> getOauth2Token( RequestDetails requestDetails, String tenant, UserDTO user) {			
		return menuService.getMenuForUser(requestDetails, new SearchCriteria()).thenApplyAsync(objectStream -> {
			try {
				Long userId = user.getId();
				String token = getSignedToken(userId, user.getUserName(), tenant);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("access_token", token);
				data.put("token_type", "Bearer");
				data.put("expires_in", EXPIRY_MINUTES*60);
				return ok(Json.toJson(data));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return forbidden();
		}, ec.current());
}
	
	private UserDTO getUser(RequestDetails requestDetails, String username) {
		UserDTO user = null;
		try {
			CompletionStage<Optional<UserDTO>> userGet = userService.getUserByName(requestDetails, username);

			Optional<UserDTO> userOpt = userGet.toCompletableFuture().get();

			if (userOpt.isPresent()) {
				user = userOpt.get();
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	private boolean isHostIpAddress(String host) {
		Pattern pattern = Pattern.compile(IPADDRESS_PATTERN);
		Matcher matcher = pattern.matcher(host);
		return matcher.matches();	 
	}

	@AddCSRFToken
	public CompletionStage<Result> resetPassword(Http.Request request) throws ExecutionException, InterruptedException {
		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setTenantName(getTenant(request));
		JsonNode body = request.body().asJson();
		if (body!= null && body.hasNonNull("username") ) {
			CompletionStage<Optional<UserDTO>> userGet = userService.getUserByName(requestDetails, body.get("username").asText());
			Optional<UserDTO> userOpt = userGet.toCompletableFuture().get();
			if (userOpt.isPresent()) {
				requestDetails.setUser(userOpt.get());
				return userService.resetPassword(requestDetails)
						.thenApplyAsync(response -> {
							return ok(Json.toJson(response));
						}, ec.current());
			}else {
				throw new BaseBusinessException(ErrorType.USER_NOT_FOUND);
			}
		}else{
			throw new BaseBusinessException(ErrorType.USER_NOT_FOUND);
		}

	}
	
}