package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.RiskType;
import com.alnt.policyengine.domain.dto.RiskTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RiskTypeServiceImpl.class)
public interface RiskTypeService extends BaseService<RiskType, RiskTypeDTO> {

}
