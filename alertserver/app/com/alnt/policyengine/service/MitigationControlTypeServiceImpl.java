package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.MitigationControlType;
import com.alnt.policyengine.domain.dto.MitigationControlTypeDTO;
import com.alnt.policyengine.mapper.MitigationControlTypeMapper;
import com.alnt.policyengine.repository.MitigationControlTypeRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MitigationControlTypeServiceImpl extends BaseServiceImpl<MitigationControlType, MitigationControlTypeDTO>
		implements MitigationControlTypeService {

	@Inject
	public MitigationControlTypeServiceImpl(HttpExecutionContext ec, MitigationControlTypeRepository repository) {
		super(ec, repository, MitigationControlTypeMapper.INSTANCE);
	}

}
