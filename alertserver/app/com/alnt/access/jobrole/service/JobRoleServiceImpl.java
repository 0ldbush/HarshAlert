package com.alnt.access.jobrole.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.access.jobrole.domain.dto.JobRoleDTO;
import com.alnt.access.jobrole.mapper.JobRoleMapper;
import com.alnt.access.jobrole.repository.JobRoleRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class JobRoleServiceImpl extends BaseServiceImpl<JobRole, JobRoleDTO> implements JobRoleService {
    
	@Inject
	public JobRoleServiceImpl(HttpExecutionContext ec, JobRoleRepository repository) {
		super( ec, repository, JobRoleMapper.INSTANCE);
	}
}
