package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.alnt.platform.core.messagemaster.mapper.MessageMasterMapper;
import com.alnt.platform.core.messagemaster.repository.MessageMasterRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MessageMasterServiceImpl extends BaseServiceImpl<MessageMaster, MessageMasterDTO> implements MessageMasterService {

	@Inject
	public MessageMasterServiceImpl(HttpExecutionContext ec, MessageMasterRepository repository) {
		super(ec, repository, MessageMasterMapper.INSTANCE);
		
	}

}
