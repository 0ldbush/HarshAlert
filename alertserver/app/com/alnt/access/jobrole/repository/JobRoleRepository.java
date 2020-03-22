package com.alnt.access.jobrole.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

@Singleton
public class JobRoleRepository extends BaseRepositoryImpl<JobRole> implements BaseRepository<JobRole> {

	@Inject
	public JobRoleRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, JobRole.class);
	}

}
