package com.alnt.platform.core.binaryresource.controller;


import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.binaryresource.domain.Attachment;
import com.alnt.platform.core.binaryresource.domain.dto.AttachmentDTO;
import com.alnt.platform.core.binaryresource.service.AttachmentService;

import play.libs.concurrent.HttpExecutionContext;

public class AttachmentController extends BaseController<Attachment, AttachmentDTO> {
	
	
		
	@Inject
	public AttachmentController(AttachmentService busObjResourceService, HttpExecutionContext ec) {
		super(busObjResourceService, ec, Attachment.class, AttachmentDTO.class);
	}

}
