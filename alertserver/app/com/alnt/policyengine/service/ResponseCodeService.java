package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.ResponseCode;
import com.alnt.policyengine.domain.dto.ResponseCodeDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ResponseCodeServiceImpl.class)
public interface ResponseCodeService extends BaseService<ResponseCode, ResponseCodeDTO> {

}
