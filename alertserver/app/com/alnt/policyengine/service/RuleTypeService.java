package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.RuleType;
import com.alnt.policyengine.domain.dto.RuleTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleTypeServiceImpl.class)
public interface RuleTypeService extends BaseService<RuleType, RuleTypeDTO> {

}
