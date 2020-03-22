package com.alnt.mdm.attachmentType.service;

import com.alnt.mdm.attachmentType.domain.AttachmentType;
import com.alnt.mdm.attachmentType.domain.dto.AttachmentTypeDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(AttachmentTypeServiceImpl.class)
public interface AttachmentTypeService extends BaseService<AttachmentType, AttachmentTypeDTO> {

}
