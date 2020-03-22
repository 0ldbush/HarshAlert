package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.ResponseCode;
import com.alnt.policyengine.domain.dto.ResponseCodeDTO;
import com.alnt.policyengine.mapper.ResponseCodeMapper;
import com.alnt.policyengine.repository.ResponseCodeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ResponseCodeServiceImpl extends BaseServiceImpl<ResponseCode, ResponseCodeDTO> implements ResponseCodeService {
    
	@Inject
	public ResponseCodeServiceImpl(HttpExecutionContext ec, ResponseCodeRepository repository) {
		super( ec, repository, ResponseCodeMapper.INSTANCE);
	}

	

}
