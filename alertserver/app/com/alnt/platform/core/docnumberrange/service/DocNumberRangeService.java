package com.alnt.platform.core.docnumberrange.service;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.docnumberrange.domain.DocNumberRange;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRangeDTO;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRequestDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(DocNumberRangeServiceImpl.class)
public interface DocNumberRangeService extends BaseService<DocNumberRange, DocNumberRangeDTO> {
	
	CompletionStage<Optional<String>> getDocNumber(RequestDetails requestDetails, DocNumberRequestDTO docNumberRequestDTO);
}
