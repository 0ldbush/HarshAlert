package com.alnt.platform.core.messagemaster.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterButtonDTO;
import com.alnt.platform.core.messagemaster.service.MessageMasterButtonService;

import play.libs.concurrent.HttpExecutionContext;

public class MessageMasterButtonController extends BaseController<MessageMasterButton, MessageMasterButtonDTO>{

	@Inject
	public MessageMasterButtonController(MessageMasterButtonService messageMasterButtonService,
			HttpExecutionContext ec) {
		super(messageMasterButtonService, ec, MessageMasterButton.class, MessageMasterButtonDTO.class);
	}
	

}
