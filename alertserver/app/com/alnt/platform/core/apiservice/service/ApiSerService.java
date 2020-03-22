package com.alnt.platform.core.apiservice.service;

import java.util.List;
import java.util.Map;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.apiservice.domain.ApiService;
import com.alnt.platform.core.apiservice.domain.dto.ApiServiceDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ApiSerServiceImpl.class)
public interface ApiSerService extends BaseService<ApiService, ApiServiceDTO> {
	
	public Object invokeApi(String apiName, Map params);
	
	public boolean invokeBusinessRule(String ruleApi, Map params);
	
	public List<ApiServiceDTO> getApisForQueue(String fromQueueName);

	Object invokeBusinessRules(Map<String, Map> rules);
	
	public ApiServiceDTO getByApiServiceCode(String apiServiceCode);
}
