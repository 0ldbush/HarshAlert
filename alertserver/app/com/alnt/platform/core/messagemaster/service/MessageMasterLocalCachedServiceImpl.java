package com.alnt.platform.core.messagemaster.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.alnt.platform.core.messagemaster.mapper.MessageMasterMapper;
import com.alnt.platform.core.messagemaster.repository.MessageMasterRepository;

import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MessageMasterLocalCachedServiceImpl extends BaseLocalCachedServiceImpl<MessageMaster, MessageMasterDTO> implements MessageMasterService {
    
	@Inject
	public MessageMasterLocalCachedServiceImpl(AsyncCacheApi caceApi, HttpExecutionContext ec, MessageMasterRepository repository) {
		super(caceApi, "MessageMaster", ec, repository, MessageMasterMapper.INSTANCE);
	}

}
