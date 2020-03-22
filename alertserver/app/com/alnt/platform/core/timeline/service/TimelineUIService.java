package com.alnt.platform.core.timeline.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.timeline.domain.TimelineUI;
import com.alnt.platform.core.timeline.domain.dto.TimelineUIDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(TimelineUIServiceImpl.class)
public interface TimelineUIService extends BaseService<TimelineUI, TimelineUIDTO> {

}
