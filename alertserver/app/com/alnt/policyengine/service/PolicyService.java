package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Policy;
import com.alnt.policyengine.domain.dto.PolicyDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(PolicyServiceImpl.class)
public interface PolicyService extends BaseService<Policy, PolicyDTO> {

}
