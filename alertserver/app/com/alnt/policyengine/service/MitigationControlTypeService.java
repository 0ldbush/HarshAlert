package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.MitigationControlType;
import com.alnt.policyengine.domain.dto.MitigationControlTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MitigationControlTypeServiceImpl.class)
public interface MitigationControlTypeService extends BaseService<MitigationControlType, MitigationControlTypeDTO> {

}
