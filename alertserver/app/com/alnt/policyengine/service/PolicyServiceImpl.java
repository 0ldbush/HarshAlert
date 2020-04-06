package com.alnt.policyengine.service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.alnt.policyengine.mapper.PolicyMapper;
import com.alnt.policyengine.repository.PolicyRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class PolicyServiceImpl extends BaseServiceImpl<Policy, PolicyDTO> implements PolicyService {
    
	@Inject
	public PolicyServiceImpl( HttpExecutionContext ec,PolicyRepository repository) {
		super( ec, repository, PolicyMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Stream<PolicyDTO>> getAllPolicyForGroups(RequestDetails requestDetails,
			List<String> groups) {
		return ((PolicyRepository)this.getDaoRepository()).getAllPolicyForGroups(requestDetails, groups).thenApplyAsync(optionalData -> {
            return optionalData.map(ee -> getMapper().entityToDTO(ee));
        }, ec.current());
	}

}
