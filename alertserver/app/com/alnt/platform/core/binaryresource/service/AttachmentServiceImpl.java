package com.alnt.platform.core.binaryresource.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.binaryresource.domain.Attachment;
import com.alnt.platform.core.binaryresource.domain.dto.AttachmentDTO;
import com.alnt.platform.core.binaryresource.mapper.AttachmentMapper;
import com.alnt.platform.core.binaryresource.repository.AttachmentRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class AttachmentServiceImpl extends BaseServiceImpl<Attachment, AttachmentDTO> implements AttachmentService {
    
	@Inject
	public AttachmentServiceImpl(HttpExecutionContext ec, AttachmentRepository repository) {
		super( ec, repository, AttachmentMapper.INSTANCE);
	}

	

}
