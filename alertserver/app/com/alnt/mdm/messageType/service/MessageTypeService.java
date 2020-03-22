package com.alnt.mdm.messageType.service;

import com.alnt.mdm.messageType.domain.MessageType;
import com.alnt.mdm.messageType.domain.dto.MessageTypeDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(MessageTypeServiceImpl.class)
public interface MessageTypeService extends BaseService<MessageType, MessageTypeDTO> {

}
