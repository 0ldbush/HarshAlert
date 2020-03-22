package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.Risk;
import com.alnt.policyengine.domain.dto.RiskDTO;
import com.alnt.policyengine.mapper.RiskMapper;
import com.alnt.policyengine.repository.RiskRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RiskServiceImpl extends BaseServiceImpl<Risk, RiskDTO> implements RiskService {
    
	@Inject
	public RiskServiceImpl(HttpExecutionContext ec, RiskRepository repository) {
		super( ec, repository, RiskMapper.INSTANCE);
	}

	

}
