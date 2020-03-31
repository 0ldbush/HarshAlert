package com.alnt.platform.base.interceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.domain.BaseSettingEntity;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRequestDTO;
import com.alnt.platform.core.docnumberrange.service.DocNumberRangeService;

import play.inject.Injector;

public class BasePreInsertEventListener implements PreInsertEventListener {

	protected Injector injector;
	
	protected DocNumberRangeService docNumberRangeService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	public BasePreInsertEventListener(Injector injector) {
		super();
		this.injector = injector;
	}

	@Override
	public boolean onPreInsert(PreInsertEvent event) {
		Object entity = event.getEntity();
		if(entity instanceof BaseMasterEntity) {
			if(StringUtils.isBlank(((BaseMasterEntity) entity).getExtId())) {
				String extID = getExtId(entity);
				((BaseMasterEntity) entity).setExtId(extID);
				
				String[] properties = event.getPersister().getEntityMetamodel().getPropertyNames();
		        List<String> propertiesList = Arrays.asList(properties);
		        event.getState()[propertiesList.indexOf("extId")] = extID;
			}
		} else if(entity instanceof BaseSettingEntity) {
			
		}
		
		return false;
	}
	
	private String getExtId(Object entity) {
		boolean generateDefault = true;
		String extId = null;
		
//		this.docNumberRangeService = injector.instanceOf(
//				new play.inject.BindingKey<DocNumberRangeService>(
//						DocNumberRangeService.class
//		).asScala());
//		if(docNumberRangeService!= null) {			
//			Optional<String> extIdOpt = null;
//			try {
//				DocNumberRequestDTO docNumberRequestDTO = new DocNumberRequestDTO();
//				//docNumberRequestDTO.setBusObjCat(busObjCat);
//				//docNumberRequestDTO.setBusObjTypeId(busObjTypeId);
//				extIdOpt = docNumberRangeService.getDocNumber(new RequestDetails(), docNumberRequestDTO).toCompletableFuture().get();
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			if(extIdOpt!= null && extIdOpt.isPresent())
//				extId = extIdOpt.get();
//		}
		if(generateDefault) {
			String prefix = entity.getClass().getSimpleName().toUpperCase().substring(0,3);
			int number = (int)(Math.random()*100000);
			String suffix = StringUtils.leftPad(number+"", 7, "0");
			extId = prefix+suffix;
		}		
		return extId;
	}
	

}
