package com.alnt.platform.core.messagemaster.controller;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.alnt.platform.core.messagemaster.service.MessageMasterService;
import com.google.inject.Inject;

import play.libs.concurrent.HttpExecutionContext;

public class MessageMasterController extends BaseController<MessageMaster, MessageMasterDTO>{

	@Inject
	public MessageMasterController(MessageMasterService messageMasterService, HttpExecutionContext ec) {
		super(messageMasterService, ec, MessageMaster.class, MessageMasterDTO.class);
	}

}
