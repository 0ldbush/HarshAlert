package com.alnt.platform.base.interceptor;

import java.util.Optional;

import javax.inject.Inject;

import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.timeline.repository.TimelineRepository;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import play.inject.Injector;
import play.inject.QualifierInstance;

public class BasePostUpdateEventListener extends BaseEventListener implements PostUpdateEventListener {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public BasePostUpdateEventListener(Injector injector) {
		super();
		this.injector = injector;
	}

	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostUpdate(PostUpdateEvent event) {
		try {
			if(this.classDefService == null) {
				this.classDefService = injector.instanceOf(ClassDefService.class);
			}
			if(this.timelineRepository == null) {
				timelineRepository = injector.instanceOf(TimelineRepository.class);
				
			}
				
			final Object entity = event.getEntity();
			if (entity instanceof com.alnt.platform.base.domain.Entity && isValidForTimeline(entity.getClass().getSimpleName())) { 
				insertTimeline(event, OPERATION_UPDATE, (com.alnt.platform.base.domain.Entity)entity, event.getId(), event.getState(), event.getOldState(), event.getDirtyProperties()); 
			}
		} catch(Throwable th) {
			
		}
		
	}
	
	
}
