package com.alnt.platform.core.navigation.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.navigation.domain.Activity;
import com.alnt.platform.core.navigation.domain.dto.ActivityDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ActivityServiceImpl.class)
public interface ActivityService extends BaseService<Activity, ActivityDTO> {

}
