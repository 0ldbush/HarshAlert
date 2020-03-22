package com.alnt.mdm.attachmentType.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.attachmentType.domain.AttachmentType;
import com.alnt.mdm.attachmentType.domain.dto.AttachmentTypeDTO;
import com.alnt.mdm.attachmentType.mapper.AttachmentTypeMapper;
import com.alnt.mdm.attachmentType.repository.AttachmentTypeRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class AttachmentTypeServiceImpl extends BaseServiceImpl<AttachmentType, AttachmentTypeDTO> implements AttachmentTypeService {
    
	@Inject
	public AttachmentTypeServiceImpl(HttpExecutionContext ec, AttachmentTypeRepository repository) {
		super( ec, repository, AttachmentTypeMapper.INSTANCE);
	}

	

}
