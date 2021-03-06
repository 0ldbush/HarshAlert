package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.PolicyGroup;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class PolicyGroupRepository extends BaseRepositoryImpl<PolicyGroup> implements BaseRepository<PolicyGroup> {

	@Inject
	public PolicyGroupRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, PolicyGroup.class);
	}

}
