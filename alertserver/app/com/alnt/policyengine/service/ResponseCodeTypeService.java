package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.ResponseCodeType;
import com.alnt.policyengine.domain.dto.ResponseCodeTypeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ResponseCodeTypeServiceImpl.class)
public interface ResponseCodeTypeService extends BaseService<ResponseCodeType, ResponseCodeTypeDTO> {

}
