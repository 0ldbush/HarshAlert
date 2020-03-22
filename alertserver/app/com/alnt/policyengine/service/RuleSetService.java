package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.RuleSet;
import com.alnt.policyengine.domain.dto.RuleSetDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleSetServiceImpl.class)
public interface RuleSetService extends BaseService<RuleSet, RuleSetDTO> {

}
