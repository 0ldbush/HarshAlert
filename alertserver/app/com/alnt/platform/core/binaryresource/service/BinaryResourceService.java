package com.alnt.platform.core.binaryresource.service;

import java.util.concurrent.CompletionStage;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.binaryresource.domain.BinaryResource;
import com.alnt.platform.core.binaryresource.domain.dto.BinaryResourceDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(BinaryResourceServiceImpl.class)
public interface BinaryResourceService extends BaseService<BinaryResource, BinaryResourceDTO> {
	CompletionStage<BinaryResourceDTO> upload(RequestDetails requestDetails, BinaryResource data);
}
