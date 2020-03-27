
package com.alnt.platform.application.security.authorization.deadbolt;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.DynamicResourceHandler;
import play.mvc.Http;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


public class NiceNameDynamicResourceHandler implements DynamicResourceHandler
{
    @Override
    public CompletionStage<Boolean> isAllowed(final String name,
                                              final Optional<String> meta,
                                              final DeadboltHandler deadboltHandler,
                                              final Http.RequestHeader requestHeader)
    {
        return deadboltHandler.getSubject(requestHeader)
                              .thenApply(option -> option.isPresent() && option.get().getIdentifier()
                                                                               .contains("greet"));
    }

    @Override
    public CompletionStage<Boolean> checkPermission(final String permissionValue,
                                                    final Optional<String> meta,
                                                    final DeadboltHandler deadboltHandler,
                                                    final Http.RequestHeader requestHeader)
    {
        return CompletableFuture.completedFuture(false);
    }
}
