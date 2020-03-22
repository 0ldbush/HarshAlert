package com.alnt.access.jobrole.service;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.access.jobrole.domain.dto.JobRoleDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(JobRoleServiceImpl.class)
public interface JobRoleService extends BaseService<JobRole, JobRoleDTO> {
	

}
