package business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.core.navigation.service.MenuService;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.service.PolicyService;
import com.google.inject.Guice;

import play.Application;
import play.ApplicationLoader.Context;
import play.Environment;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;

// Use FixMethodOrder to run the tests sequentially
public class ApplicationSetup {

	 // #test-injection
	  @Inject Application application;
	  
	  @Inject MenuService menuService;
	  
	  @Before
	  public void setup() {
//	    Module testModule =
//	        new PolicyEngineModule;
			
			
	    GuiceApplicationBuilder builder =
	        new GuiceApplicationLoader()
	            .builder(new Context(Environment.simple()));
	            //.overrides(testModule);
	    Guice.createInjector(builder.applicationModule()).injectMembers(this);
//
	    Helpers.start(application);
	  }
	
	@Test
    public void TestCode() throws Exception{
        assertNotNull(application);
        
        PolicyService policyService = application.injector().instanceOf(PolicyService.class);
        
        assertNotNull(policyService);
        
       //Optional<PolicyDTO> optional = policyService.get(new RequestDetails(),1l).toCompletableFuture().get();
       
       // assertFalse(optional.isPresent());
        
       // assertEquals(optional.get().getDescription(), "fdd");
        
    }

}