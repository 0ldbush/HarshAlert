
package com.alnt.platform.application.security.authorization.deadbolt;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alnt.access.user.domain.dto.SecurityPermission;
import com.alnt.access.user.service.UserService;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.Constants;
import be.objectify.deadbolt.java.ConstraintPoint;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Permission;
import be.objectify.deadbolt.java.models.Subject;
import play.mvc.Http;
import play.mvc.Result;


@HandlerQualifiers.MainHandler
public class MyDeadboltHandler extends AbstractDeadboltHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MyDeadboltHandler.class);

    private final DynamicResourceHandler dynamicHandler;
    private final UserService userService;

    @Inject
    public MyDeadboltHandler(final UserService userService)
    {
        super();
        Map<String, DynamicResourceHandler> delegates = new HashMap<>();
        delegates.put("niceName",
                      new NiceNameDynamicResourceHandler());
        this.dynamicHandler = new CompositeDynamicResourceHandler(delegates);
        this.userService = userService;
    }

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.RequestHeader requestHeader)
    {
        final Optional<Http.Cookie> maybeUserCookie = Optional.ofNullable(requestHeader.cookie("user"));
        //return CompletableFuture.supplyAsync(() -> maybeUserCookie.flatMap(cookie -> userRepository.getBy(cookie.value())));
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public CompletionStage<Optional<Result>> beforeAuthCheck(final Http.RequestHeader requestHeader, final Optional<String> content)
    {
        return CompletableFuture.completedFuture(Optional.empty());
    }

    @Override
    public CompletionStage<Optional<DynamicResourceHandler>> getDynamicResourceHandler(final Http.RequestHeader requestHeader)
    {
        return CompletableFuture.supplyAsync(() -> Optional.of(dynamicHandler));
    }

    @Override
    public CompletionStage<List<? extends Permission>> getPermissionsForRole(final String roleName) {
        return CompletableFuture.completedFuture(Collections.singletonList(new SecurityPermission("killer.undead.*")));
    }

    @Override
    public String handlerName()
    {
        return Constants.DEFAULT_HANDLER_KEY;
    }

    @Override
    public void onAuthSuccess(Http.RequestHeader requestHeader,
                              String constraintType,
                              ConstraintPoint constraintPoint)
    {
        LOGGER.info("[{} - {}] - authorization succeeded for [{}]",
                    constraintPoint,
                    constraintType,
                    requestHeader.attrs());
    }
}
