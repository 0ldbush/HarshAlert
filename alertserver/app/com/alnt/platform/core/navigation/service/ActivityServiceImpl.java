package com.alnt.platform.core.navigation.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.navigation.domain.Activity;
import com.alnt.platform.core.navigation.domain.dto.ActivityDTO;
import com.alnt.platform.core.navigation.mapper.ActivityMapper;
import com.alnt.platform.core.navigation.repository.ActivityRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ActivityServiceImpl extends BaseServiceImpl<Activity, ActivityDTO> implements ActivityService {
    
	@Inject
	public ActivityServiceImpl(HttpExecutionContext ec, ActivityRepository repository) {
		super( ec, repository, ActivityMapper.INSTANCE);
	}

	

}
