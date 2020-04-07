package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.request.Criteria;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.alnt.platform.core.messagemaster.mapper.MessageMasterMapper;
import com.alnt.platform.core.messagemaster.repository.MessageMasterRepository;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import org.springframework.data.domain.Page;
import play.libs.concurrent.HttpExecutionContext;

import java.util.concurrent.CompletionStage;

@Singleton
public class MessageMasterServiceImpl extends BaseServiceImpl<MessageMaster, MessageMasterDTO> implements MessageMasterService {

	@Inject
	public MessageMasterServiceImpl(HttpExecutionContext ec, MessageMasterRepository repository) {
		super(ec, repository, MessageMasterMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Page<MessageMaster>> getMessage(RequestDetails requestDetails, ApiMessageType apiMessageType, String messageCode) {
		SearchCriteria searchCriteria=new SearchCriteria();
		Criteria type=new Criteria("type",apiMessageType.toString());
		Criteria code=new Criteria("extId",messageCode);
		searchCriteria.getFilterCriteria().add(type);
		searchCriteria.getFilterCriteria().add(code);
		return  getDaoRepository().findAll(requestDetails, searchCriteria);

	}

	
}
