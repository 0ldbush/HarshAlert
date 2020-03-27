
package com.alnt.platform.application.security.authorization.modules;

import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import com.alnt.access.user.service.UserService;
import com.alnt.access.user.service.UserServiceImpl;
import com.alnt.platform.application.security.authorization.deadbolt.CompositeConstraints;
import com.alnt.platform.application.security.authorization.deadbolt.HandlerQualifiers;
import com.alnt.platform.application.security.authorization.deadbolt.MyCustomTemplateFailureListener;
import com.alnt.platform.application.security.authorization.deadbolt.MyDeadboltHandler;
import com.alnt.platform.application.security.authorization.deadbolt.MyHandlerCache;
import com.alnt.platform.application.security.authorization.deadbolt.SomeOtherDeadboltHandler;
import com.typesafe.config.Config;

import be.objectify.deadbolt.java.DeadboltHandler;
import be.objectify.deadbolt.java.TemplateFailureListener;
import be.objectify.deadbolt.java.cache.HandlerCache;
import play.Environment;
import play.inject.Binding;
import play.inject.Module;


public class CustomDeadboltHook extends Module
{
    @Override
    public List<Binding<?>> bindings(final Environment environment,
                                     final Config config)
    {
        return Arrays.asList(bindClass(TemplateFailureListener.class).to(MyCustomTemplateFailureListener.class).in(Singleton.class),
                             bindClass(UserService.class).to(UserServiceImpl.class).in(Singleton.class),
                             bindClass(DeadboltHandler.class).qualifiedWith(HandlerQualifiers.MainHandler.class).to(MyDeadboltHandler.class).in(Singleton.class),
                             bindClass(DeadboltHandler.class).qualifiedWith(HandlerQualifiers.SomeOtherHandler.class).to(SomeOtherDeadboltHandler.class).in(Singleton.class),
                             bindClass(HandlerCache.class).to(MyHandlerCache.class).in(Singleton.class),
                             bindClass(CompositeConstraints.class).toSelf().eagerly());
    }
}
