package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.MitigationControl;
import com.alnt.policyengine.domain.dto.MitigationControlDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MitigationControlServiceImpl.class)
public interface MitigationControlService extends BaseService<MitigationControl, MitigationControlDTO> {

}
