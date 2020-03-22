package com.alnt.platform.base.interceptor;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.event.spi.PreInsertEvent;
import org.hibernate.event.spi.PreInsertEventListener;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.domain.BaseSettingEntity;

public class BasePreInsertEventListener implements PreInsertEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		String prefix = entity.getClass().getSimpleName().toUpperCase().substring(0,3);
		int number = (int)(Math.random()*100000);
		String suffix = StringUtils.leftPad(number+"", 7, "0");
		String extId = prefix+suffix;
		
		return extId;
	}
	

}
