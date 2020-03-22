package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.PolicyGroup;
import com.alnt.policyengine.domain.dto.PolicyGroupDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(PolicyGroupServiceImpl.class)
public interface PolicyGroupService extends BaseService<PolicyGroup, PolicyGroupDTO> {

}
