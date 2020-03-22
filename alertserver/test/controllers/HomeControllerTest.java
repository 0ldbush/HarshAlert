package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.core.navigation.service.MenuService;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.service.PolicyService;
import com.google.inject.Guice;

import play.Application;
import play.Environment;
import play.ApplicationLoader.Context;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;
import play.test.Helpers;
import play.test.WithApplication;

public class HomeControllerTest  {

	final Logger Logger = LoggerFactory.getLogger(this.getClass());
	
	 // #test-injection
	  @Inject Application application;
	  
	  @Inject MenuService menuService;
	  
//	    @Override
//	    protected Application provideApplication() {
//	       // return new GuiceApplicationBuilder().build();
//	    }

	    
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
    public void testPolicy() throws InterruptedException, ExecutionException {
    	
    	PolicyService policy = application.injector().instanceOf(PolicyService.class);
    	
    	assertTrue(policy != null);
    	
    	PolicyDTO d = new PolicyDTO();
    	d.setExtId("JunitPolicy");
    	d.setText("JunitPolicy");
    	d.setIntStatus(0);
    	CompletionStage<Optional<PolicyDTO>> save = policy.save(new RequestDetails(), d);
    	Optional<PolicyDTO> join2 = save.toCompletableFuture().join();
    	
    	assertTrue(join2.isPresent());
//    	CompletionStage<Stream<PolicyDTO>> policies = policy.find(new RequestDetails(), new SearchCriteria());
//    	Stream<PolicyDTO> join = policies.toCompletableFuture().join();
//		assertTrue(join !=null);
    //	Optional<PolicyDTO> optional = policy.get(new RequestDetails(),1l).toCompletableFuture().get();
    	
    	
   // 	assertFalse(optional.isPresent());
    	//assertEquals("aa", optional.get().getDescription());
    	
  //  	Logger.info("==================" + optional.get().getDescription());
    	
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/");
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
    }
    
//    @Test
//    public void testIndex() {
//        Http.RequestBuilder request = new Http.RequestBuilder()
//                .method(GET)
//                .uri("/");
//
//        Result result = route(app, request);
//        assertEquals(OK, result.status());
//    }

}
