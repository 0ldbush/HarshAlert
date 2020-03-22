package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.mapper.PolicyMapper;
import com.alnt.policyengine.repository.PolicyRepository;

import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class PolicyServiceImpl extends BaseLocalCachedServiceImpl<Policy, PolicyDTO> implements PolicyService {
    
	@Inject
	public PolicyServiceImpl(AsyncCacheApi cache, HttpExecutionContext ec, PolicyRepository repository) {
		super( cache,"Policy",ec, repository, PolicyMapper.INSTANCE);
	}

	

}
