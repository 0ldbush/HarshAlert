package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.MitigationControl;
import com.alnt.policyengine.domain.dto.MitigationControlDTO;
import com.alnt.policyengine.mapper.MitigationControlMapper;
import com.alnt.policyengine.repository.MitigationControlRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class MitigationControlServiceImpl extends BaseServiceImpl<MitigationControl, MitigationControlDTO> implements MitigationControlService {
    
	@Inject
	public MitigationControlServiceImpl(HttpExecutionContext ec, MitigationControlRepository repository) {
		super( ec, repository, MitigationControlMapper.INSTANCE);
	}

	

}
