package com.alnt.mdm.messageType.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.messageType.domain.MessageType;
import com.alnt.mdm.messageType.domain.dto.MessageTypeDTO;
import com.alnt.mdm.messageType.mapper.MessageTypeMapper;
import com.alnt.mdm.messageType.repository.MessageTypeRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MessageTypeServiceImpl extends BaseServiceImpl<MessageType, MessageTypeDTO> implements MessageTypeService {
    
	@Inject
	public MessageTypeServiceImpl(HttpExecutionContext ec, MessageTypeRepository repository) {
		super( ec, repository, MessageTypeMapper.INSTANCE);
	}

	

}
