
package com.alnt.platform.application.security.authorization.deadbolt;

import be.objectify.deadbolt.java.AbstractDeadboltHandler;
import be.objectify.deadbolt.java.ConstraintPoint;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import be.objectify.deadbolt.java.models.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import play.mvc.Http;
import play.mvc.Result;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@HandlerQualifiers.SomeOtherHandler
public class SomeOtherDeadboltHandler extends AbstractDeadboltHandler
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SomeOtherDeadboltHandler.class);

    @Override
    public CompletionStage<Optional<? extends Subject>> getSubject(final Http.RequestHeader requestHeader)
    {
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
        return CompletableFuture.completedFuture(Optional.empty());
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
