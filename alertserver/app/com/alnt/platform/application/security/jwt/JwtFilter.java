package com.alnt.platform.application.security.jwt;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.stream.Materializer;
import play.libs.F;
import play.mvc.Filter;
import play.mvc.Http;
import play.mvc.Result;
import play.routing.HandlerDef;
import play.routing.Router;

import static play.mvc.Results.forbidden;

public class JwtFilter extends Filter {
	final Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	
    private static final String BEARER = "Bearer ";
    private static final String ROUTE_MODIFIER_NO_JWT_FILTER_TAG = "noJwtFilter";
    private static final String ERR_AUTHORIZATION_HEADER = "ERR_AUTHORIZATION_HEADER";
    private JwtValidator jwtValidator;

    @Inject
    public JwtFilter(Materializer mat, JwtValidator jwtValidator) {
        super(mat);
        this.jwtValidator = jwtValidator;
    }

    @Override
    public CompletionStage<Result> apply(Function<Http.RequestHeader, CompletionStage<Result>> nextFilter, Http.RequestHeader requestHeader) {
        if (requestHeader.attrs().containsKey(Router.Attrs.HANDLER_DEF)) {
            HandlerDef handler = requestHeader.attrs().get(Router.Attrs.HANDLER_DEF);
            List<String> modifiers = handler.getModifiers();

            if (modifiers.contains(ROUTE_MODIFIER_NO_JWT_FILTER_TAG)) {
                return nextFilter.apply(requestHeader);
            }
        }

        Optional<String> authHeader =  requestHeader.getHeaders().get(Http.HeaderNames.AUTHORIZATION);
        
        //Tempfor download api.. need to remove later -- Anuj
        String headerToken = requestHeader.queryString().get("Authorization") != null && requestHeader.queryString().get("Authorization").length > 0 ? requestHeader.queryString().get("Authorization")[0].replace(BEARER, "") : "";
        if (!authHeader.filter(ah -> ah.contains(BEARER)).isPresent()) {
        	if(StringUtils.isNotBlank(headerToken)) {
        		
        	} else {
	            Logger.error("f=JwtFilter, error=authHeaderNotPresent");
	            return CompletableFuture.completedFuture(forbidden(ERR_AUTHORIZATION_HEADER));
        	}
        }

        String token = authHeader.map(ah -> ah.replace(BEARER, "")).orElse(headerToken);
        F.Either<JwtValidator.Error, VerifiedJwt> res = jwtValidator.verify(token);

        if (res.left.isPresent()) {
            return CompletableFuture.completedFuture(forbidden(res.left.get().toString()));
        }

        return nextFilter.apply(requestHeader.withAttrs(requestHeader.attrs()
        		.put(Attrs.VERIFIED_JWT, res.right.get()
        				)));
    }
}