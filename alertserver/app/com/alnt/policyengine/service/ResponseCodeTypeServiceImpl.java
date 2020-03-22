package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.ResponseCodeType;
import com.alnt.policyengine.domain.dto.ResponseCodeTypeDTO;
import com.alnt.policyengine.mapper.ResponseCodeTypeMapper;
import com.alnt.policyengine.repository.ResponseCodeTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ResponseCodeTypeServiceImpl extends BaseServiceImpl<ResponseCodeType, ResponseCodeTypeDTO>
		implements ResponseCodeTypeService {

	@Inject
	public ResponseCodeTypeServiceImpl(HttpExecutionContext ec, ResponseCodeTypeRepository repository) {
		super(ec, repository, ResponseCodeTypeMapper.INSTANCE);
	}

}
