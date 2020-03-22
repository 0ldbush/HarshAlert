package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Risk;
import com.alnt.policyengine.domain.dto.RiskDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RiskServiceImpl.class)
public interface RiskService extends BaseService<Risk, RiskDTO> {

}
