package com.alnt.platform.core.navigation.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.navigation.domain.Activity;
import com.alnt.platform.core.navigation.domain.dto.ActivityDTO;
import com.alnt.platform.core.navigation.service.ActivityService;

import play.libs.concurrent.HttpExecutionContext;

public class ActivityController extends BaseController<Activity, ActivityDTO> {
	
	
		
	@Inject
	public ActivityController(ActivityService activityService, HttpExecutionContext ec) {
		super(activityService, ec, Activity.class, ActivityDTO.class);
	}

}
