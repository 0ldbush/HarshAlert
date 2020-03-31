package com.alnt.platform.base.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.spi.AbstractEvent;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.domain.BaseSettingEntity;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.core.docnumberrange.service.DocNumberRangeService;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import play.inject.Injector;
import play.inject.QualifierInstance;

public class BasePreInsertEventListener extends BaseEventListener implements PreInsertEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected DocNumberRangeService docNumberRangeService;
	
	@Inject
	public BasePreInsertEventListener(Injector injector) {
		super();
		this.injector = injector;
	}
	
	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		if(this.docNumberRangeService == null) {
			this.docNumberRangeService = injector.instanceOf(
					new play.inject.BindingKey<DocNumberRangeService>(
							DocNumberRangeService.class, 
							Optional.of(new QualifierInstance<Named>(Names.named("base")))
			).asScala());
		}
		
		Object entity = event.getEntity();
		if(entity instanceof BaseMasterEntity) {
			if(StringUtils.isBlank(((BaseMasterEntity) entity).getExtId())) {
				entity = setExtId(entity, event);
				String extId = ((BaseMasterEntity) entity).getExtId();
				
				String[] properties = event.getPersister().getEntityMetamodel().getPropertyNames();
		        List<String> propertiesList = Arrays.asList(properties);
		        event.getState()[propertiesList.indexOf("extId")] = extId;
			}
		} else if(entity instanceof BaseSettingEntity) {
			
		}
		
		return false;
	}
	
	private Object setExtId(Object entity, AbstractEvent event) {
		//boolean generateDefault = false;
		//String extId = null;
		Optional<Object> extIdOpt = null;
		RequestDetails requestDetails = new RequestDetails();
		requestDetails.setTenantName((String) event.getSession().getFactory().getProperties().get("alert.tenantName"));
		try {
			extIdOpt = this.docNumberRangeService.getDocNumber(requestDetails, entity).toCompletableFuture().get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		

		/*if(generateDefault) {
			String prefix = entity.getClass().getSimpleName().toUpperCase().substring(0,3);
			int number = (int)(Math.random()*100000);
			String suffix = StringUtils.leftPad(number+"", 7, "0");
			extId = prefix+suffix;
		}*/		
		return extIdOpt.get();
	}
	

}
