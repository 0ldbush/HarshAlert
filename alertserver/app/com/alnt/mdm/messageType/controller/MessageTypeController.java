package com.alnt.mdm.messageType.controller;


import javax.inject.Inject;

import com.alnt.mdm.messageType.domain.MessageType;
import com.alnt.mdm.messageType.domain.dto.MessageTypeDTO;
import com.alnt.mdm.messageType.service.MessageTypeService;
import com.alnt.platform.base.controller.BaseController;

import play.libs.concurrent.HttpExecutionContext;

public class MessageTypeController extends BaseController<MessageType, MessageTypeDTO> {
	
	
		
	@Inject
	public MessageTypeController(MessageTypeService messageTypeService, HttpExecutionContext ec) {
		super(messageTypeService, ec, MessageType.class, MessageTypeDTO.class);
	}

}
