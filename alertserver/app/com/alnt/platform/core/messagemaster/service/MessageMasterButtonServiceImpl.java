package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterButtonDTO;
import com.alnt.platform.core.messagemaster.mapper.MessageMasterButtonMapper;
import com.alnt.platform.core.messagemaster.repository.MessageMasterButtonRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MessageMasterButtonServiceImpl extends BaseServiceImpl<MessageMasterButton, MessageMasterButtonDTO> implements MessageMasterButtonService {

	@Inject
	public MessageMasterButtonServiceImpl(HttpExecutionContext ec, MessageMasterButtonRepository repository) {
		super(ec, repository, MessageMasterButtonMapper.INSTANCE);
	}
	
	
}
