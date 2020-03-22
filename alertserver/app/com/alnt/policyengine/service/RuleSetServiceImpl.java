package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.RuleSet;
import com.alnt.policyengine.domain.dto.RuleSetDTO;
import com.alnt.policyengine.mapper.RuleSetMapper;
import com.alnt.policyengine.repository.RuleSetRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleSetServiceImpl extends BaseServiceImpl<RuleSet, RuleSetDTO> implements RuleSetService {
    
	@Inject
	public RuleSetServiceImpl(HttpExecutionContext ec, RuleSetRepository repository) {
		super( ec, repository, RuleSetMapper.INSTANCE);
	}

	

}
