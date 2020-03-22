package com.alnt.platform.core.timeline.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.timeline.domain.TimelineUI;
import com.alnt.platform.core.timeline.domain.dto.TimelineUIDTO;
import com.alnt.platform.core.timeline.service.TimelineUIService;

import play.libs.concurrent.HttpExecutionContext;

public class TimelineUIController extends BaseController<TimelineUI, TimelineUIDTO> {

	@Inject
	public TimelineUIController(TimelineUIService timelineUIService, HttpExecutionContext ec) {
		super(timelineUIService, ec, TimelineUI.class, TimelineUIDTO.class);
	}
}
