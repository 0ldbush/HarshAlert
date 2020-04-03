package com.alnt.platform.base.interceptor;

import java.util.Optional;

import javax.inject.Inject;

import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

import com.alnt.platform.core.classdef.service.ClassDefService;
import com.alnt.platform.core.timeline.repository.TimelineRepository;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

import play.inject.Injector;
import play.inject.QualifierInstance;

public class BasePostInsertEventListener extends BaseEventListener implements PostInsertEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	public BasePostInsertEventListener(Injector injector) {
		super();
		this.injector = injector;
	}
	
	@Override
	public boolean requiresPostCommitHanding(EntityPersister persister) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onPostInsert(PostInsertEvent event) {
		if(this.classDefService == null) {
			this.classDefService = injector.instanceOf(ClassDefService.class);
		}
		if(this.timelineRepository == null) {
			timelineRepository = injector.instanceOf(TimelineRepository.class);
			
		}
			
		final Object entity = event.getEntity();
		if (entity instanceof com.alnt.platform.base.domain.Entity && isValidForTimeline(entity.getClass().getSimpleName())) { 
			insertTimeline(event, OPERATION_CREATE, (com.alnt.platform.base.domain.Entity)entity, event.getId(), event.getState(),null,null); 
		}

	}

}
