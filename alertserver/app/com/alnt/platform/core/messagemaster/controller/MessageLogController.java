package com.alnt.platform.core.messagemaster.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;
import com.alnt.platform.core.messagemaster.service.MessageLogService;

import play.libs.concurrent.HttpExecutionContext;

public class MessageLogController extends BaseController<MessageLog, MessageLogDTO> {
	
	
		
	@Inject
	public MessageLogController(MessageLogService userService, HttpExecutionContext ec) {
		super(userService, ec, MessageLog.class, MessageLogDTO.class);
	}

}
