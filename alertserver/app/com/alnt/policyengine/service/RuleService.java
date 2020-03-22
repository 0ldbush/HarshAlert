package com.alnt.policyengine.service;

import java.util.concurrent.CompletionStage;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiResponse;
import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.policyengine.domain.dto.UploadRuleDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleServiceImpl.class)
public interface RuleService extends BaseService<Rule, RuleDTO> {
	

	CompletionStage<ApiResponse> uploadExcel(RequestDetails requestDetails,UploadRuleDTO uploadRuleDTO) ;

}
