package com.alnt.policyengine.service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(PolicyServiceImpl.class)
public interface PolicyService extends BaseService<Policy, PolicyDTO> {

	CompletionStage<Stream<PolicyDTO>> getAllPolicyForGroups(RequestDetails requestDetails, List<String> groups);
}
