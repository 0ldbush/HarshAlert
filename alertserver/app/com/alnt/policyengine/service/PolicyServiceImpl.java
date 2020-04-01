package com.alnt.policyengine.service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.request.RequestDetails;
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
    
	PolicyRepository repository;
	@Inject
	public PolicyServiceImpl(AsyncCacheApi cache, HttpExecutionContext ec, PolicyRepository repository) {
		super( cache,"Policy",ec, repository, PolicyMapper.INSTANCE);
		this.repository = repository;
	}

	@Override
	public CompletionStage<Stream<PolicyDTO>> getAllPolicyForGroups(RequestDetails requestDetails,
			List<String> groups) {
		return this.repository.getAllPolicyForGroups(requestDetails, groups).thenApplyAsync(optionalData -> {
            return optionalData.map(ee -> getMapper().entityToDTO(ee));
        }, ec.current());
	}

	

}
