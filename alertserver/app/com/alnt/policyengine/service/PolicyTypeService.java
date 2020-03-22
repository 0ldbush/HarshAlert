package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.PolicyType;
import com.alnt.policyengine.domain.dto.PolicyTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(PolicyTypeServiceImpl.class)
public interface PolicyTypeService extends BaseService<PolicyType, PolicyTypeDTO> {

}
