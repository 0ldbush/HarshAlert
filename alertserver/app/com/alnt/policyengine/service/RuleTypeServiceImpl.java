package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.RuleType;
import com.alnt.policyengine.domain.dto.RuleTypeDTO;
import com.alnt.policyengine.mapper.RuleTypeMapper;
import com.alnt.policyengine.repository.RuleTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleTypeServiceImpl extends BaseServiceImpl<RuleType, RuleTypeDTO> implements RuleTypeService {

	@Inject
	public RuleTypeServiceImpl(HttpExecutionContext ec, RuleTypeRepository repository) {
		super(ec, repository, RuleTypeMapper.INSTANCE);
	}

}
