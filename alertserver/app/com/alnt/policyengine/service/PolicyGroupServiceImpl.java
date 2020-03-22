package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.PolicyGroup;
import com.alnt.policyengine.domain.dto.PolicyGroupDTO;
import com.alnt.policyengine.mapper.PolicyGroupMapper;
import com.alnt.policyengine.repository.PolicyGroupRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class PolicyGroupServiceImpl extends BaseServiceImpl<PolicyGroup, PolicyGroupDTO> implements PolicyGroupService {
    
	@Inject
	public PolicyGroupServiceImpl(HttpExecutionContext ec, PolicyGroupRepository repository) {
		super( ec, repository, PolicyGroupMapper.INSTANCE);
	}

	

}
