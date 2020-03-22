package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.RiskType;
import com.alnt.policyengine.domain.dto.RiskTypeDTO;
import com.alnt.policyengine.mapper.RiskTypeMapper;
import com.alnt.policyengine.repository.RiskTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RiskTypeServiceImpl extends BaseServiceImpl<RiskType, RiskTypeDTO> implements RiskTypeService {

	@Inject
	public RiskTypeServiceImpl(HttpExecutionContext ec, RiskTypeRepository repository) {
		super(ec, repository, RiskTypeMapper.INSTANCE);
	}

}
