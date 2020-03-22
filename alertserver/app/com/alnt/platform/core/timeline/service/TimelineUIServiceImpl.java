package com.alnt.platform.core.timeline.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.timeline.domain.TimelineUI;
import com.alnt.platform.core.timeline.domain.dto.TimelineUIDTO;
import com.alnt.platform.core.timeline.mapper.TimelineUIMapper;
import com.alnt.platform.core.timeline.repository.TimelineUIRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class TimelineUIServiceImpl extends BaseServiceImpl<TimelineUI, TimelineUIDTO> implements TimelineUIService {
    
	@Inject
	public TimelineUIServiceImpl(HttpExecutionContext ec, TimelineUIRepository repository) {
		super( ec, repository, TimelineUIMapper.INSTANCE);
	}

	

}
