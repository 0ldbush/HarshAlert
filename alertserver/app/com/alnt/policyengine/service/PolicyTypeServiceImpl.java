package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.PolicyType;
import com.alnt.policyengine.domain.dto.PolicyTypeDTO;
import com.alnt.policyengine.mapper.PolicyTypeMapper;
import com.alnt.policyengine.repository.PolicyTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class PolicyTypeServiceImpl extends BaseServiceImpl<PolicyType, PolicyTypeDTO> implements PolicyTypeService {
    
	@Inject
	public PolicyTypeServiceImpl(HttpExecutionContext ec, PolicyTypeRepository repository) {
		super( ec, repository, PolicyTypeMapper.INSTANCE);
	}

	

}
