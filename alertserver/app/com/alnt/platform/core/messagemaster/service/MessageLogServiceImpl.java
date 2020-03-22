package com.alnt.platform.core.messagemaster.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;
import com.alnt.platform.core.messagemaster.mapper.MessageLogMapper;
import com.alnt.platform.core.messagemaster.repository.MessageLogRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MessageLogServiceImpl extends BaseServiceImpl<MessageLog, MessageLogDTO> implements MessageLogService {
    
	@Inject
	public MessageLogServiceImpl(HttpExecutionContext ec, MessageLogRepository repository) {
		super( ec, repository, MessageLogMapper.INSTANCE);
	}

	

}
