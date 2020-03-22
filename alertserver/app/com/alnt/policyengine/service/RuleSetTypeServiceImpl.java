package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.RuleSetType;
import com.alnt.policyengine.domain.dto.RuleSetTypeDTO;
import com.alnt.policyengine.mapper.RuleSetTypeMapper;
import com.alnt.policyengine.repository.RuleSetTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleSetTypeServiceImpl extends BaseServiceImpl<RuleSetType, RuleSetTypeDTO> implements RuleSetTypeService {

	@Inject
	public RuleSetTypeServiceImpl(HttpExecutionContext ec, RuleSetTypeRepository repository) {
		super(ec, repository, RuleSetTypeMapper.INSTANCE);
	}

}
