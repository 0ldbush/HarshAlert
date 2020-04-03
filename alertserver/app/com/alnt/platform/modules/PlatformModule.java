package com.alnt.platform.modules;
import com.alnt.platform.application.security.jwt.JwtValidator;
import com.alnt.platform.application.security.jwt.JwtValidatorImpl;
import com.alnt.platform.core.configsetting.service.ConfigSettingLocalCachedServiceImpl;
import com.alnt.platform.core.configsetting.service.ConfigSettingService;
import com.alnt.platform.core.configsetting.service.ConfigSettingServiceImpl;
import com.alnt.platform.core.docnumberrange.service.DocNumberRangeService;
import com.alnt.platform.core.docnumberrange.service.DocNumberRangeServiceImpl;
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

	public static final String BASE = "base";
	public static final String LOCAL_CACHE = "localcache";
	
    @Override
    public void configure() {
    	bind(JwtValidator.class).to(JwtValidatorImpl.class).asEagerSingleton();
    	
		
    	
    	bind(ConfigSettingService.class).annotatedWith(Names.named(BASE)).to(ConfigSettingServiceImpl.class);
		bind(ConfigSettingService.class).annotatedWith(Names.named(LOCAL_CACHE)).to(ConfigSettingLocalCachedServiceImpl.class);
		bind(DocNumberRangeService.class).annotatedWith(Names.named(BASE)).to(DocNumberRangeServiceImpl.class);
//    	bind(UpdateEventListener.class).to(UpdateEventListener.class).asEagerSingleton();
    }
}
