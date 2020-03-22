package com.alnt.ruleengine.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.domain.dto.DefaultOutput;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleEngineServiceImpl.class)
public interface RuleEngineService extends BaseService<Rule, RuleDTO> {

	//CompletableFuture<List<DefaultOutput>> applyRules(RequestDetails requestDetails, Map<String, Object> map, String policyGroup);

	CompletableFuture<DefaultOutput> evaluateRule(RequestDetails requestDetails, Map<String, Object> map, Long ruleId);

	CompletableFuture<String> evaluateExpr(String expr);

	CompletableFuture<List<DefaultOutput>> applyRules(RequestDetails requestDetails, Map<String, Object> map,
			List<String> policyGroup);

}
