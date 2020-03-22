package com.alnt.platform.core.binaryresource.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.binaryresource.domain.Attachment;
import com.alnt.platform.core.binaryresource.domain.dto.AttachmentDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(AttachmentServiceImpl.class)
public interface AttachmentService extends BaseService<Attachment, AttachmentDTO> {

}
