package com.alnt.identity.role.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.identity.role.domain.IdentityRole;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

@Singleton
public class IdentityRoleRepository extends BaseRepositoryImpl<IdentityRole> implements BaseRepository<IdentityRole> {

	@Inject
	public IdentityRoleRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, IdentityRole.class);
	}

}
