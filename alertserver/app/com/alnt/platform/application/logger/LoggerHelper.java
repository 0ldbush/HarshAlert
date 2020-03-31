package com.alnt.platform.application.logger;

import javax.inject.Inject;

import com.alnt.platform.application.logger.service.AppLogService;
import com.alnt.platform.application.logger.service.Logger;
import com.alnt.platform.application.logger.service.LoggerImpl;

public class LoggerHelper {
	AppLogService appLogService;
     @Inject	
	public LoggerHelper(AppLogService appLogService) {
		super();
		this.appLogService = appLogService;
	}
   
	public  Logger getLogger(Class<?> classz)
	{
		return new LoggerImpl(appLogService,classz.getName());
		
	}
	
}
