package com.alnt.platform.base.interceptor;

import javax.inject.Inject;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

import play.inject.Injector;

public class BasePreUpdateEventListener extends BaseEventListener implements PreUpdateEventListener {

	

	@Inject
	public BasePreUpdateEventListener(Injector injector) {
		super();
		this.injector = injector;
	}

	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
