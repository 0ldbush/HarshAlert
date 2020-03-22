package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.RuleSetType;
import com.alnt.policyengine.domain.dto.RuleSetTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleSetTypeServiceImpl.class)
public interface RuleSetTypeService extends BaseService<RuleSetType, RuleSetTypeDTO> {

}
