package com.alnt.platform.modules;
import com.alnt.platform.application.security.jwt.JwtValidator;
import com.alnt.platform.application.security.jwt.JwtValidatorImpl;
import com.alnt.platform.base.interceptor.BasePostUpdateEventListener;
import com.alnt.platform.core.classdef.service.ClassDefLocalCachedServiceImpl;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.classdef.service.ClassDefServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * This class is a Guice module that tells Guice how to bind several
 * different types. This Guice module is created when the Play
 * application starts.
 *
 * Play will automatically use any class called `Module` that is in
 * the root package. You can create modules in other locations by
 * adding `play.modules.enabled` settings to the `application.conf`
 * configuration file.
 */
public class PlatformModule extends AbstractModule {

	public static final String CLASSDEF_BASE = "base";
	public static final String CLASSDEF_LOCAL_CACHE = "localcache";
	
    @Override
    public void configure() {
    	bind(JwtValidator.class).to(JwtValidatorImpl.class).asEagerSingleton();
    	
    	bind(ClassDefService.class).annotatedWith(Names.named(CLASSDEF_BASE)).to(ClassDefServiceImpl.class);
    	bind(ClassDefService.class).annotatedWith(Names.named(CLASSDEF_LOCAL_CACHE)).to(ClassDefLocalCachedServiceImpl.class);
    	
//    	bind(UpdateEventListener.class).to(UpdateEventListener.class).asEagerSingleton();
    }
}
