package com.alnt.platform.base.interceptor;

import javax.inject.Inject;

import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class EventListenerIntegrator implements Integrator {

	@Inject
	BasePostUpdateEventListener updateEventListener;
	
	@Inject
	BasePostInsertEventListener insertEventListener;
	
	@Inject
	BasePostDeleteEventListener deleteEventListener;
	
	@Inject
	BasePreUpdateEventListener preUpdateEventListener;
	
	@Inject
	BasePreInsertEventListener preInsertEventListener;
	
	
	@Override
	public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		final EventListenerRegistry eventListenerRegistry =
                serviceRegistry.getService(EventListenerRegistry.class);
 
		eventListenerRegistry.appendListeners(
            EventType.PRE_INSERT,
            preInsertEventListener
        );
         
        eventListenerRegistry.appendListeners(
            EventType.PRE_UPDATE,
            preUpdateEventListener
        );
        
        
		
		eventListenerRegistry.appendListeners(
            EventType.POST_INSERT,
            insertEventListener
        );
         
        eventListenerRegistry.appendListeners(
            EventType.POST_UPDATE,
            updateEventListener
        );
         
        eventListenerRegistry.appendListeners(
            EventType.POST_DELETE,
            deleteEventListener
        );

	}

	@Override
	public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
		// TODO Auto-generated method stub

	}

}
