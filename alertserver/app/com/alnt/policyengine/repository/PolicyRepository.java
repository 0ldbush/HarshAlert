package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.Policy;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class PolicyRepository extends BaseRepositoryImpl<Policy> implements BaseRepository<Policy> {

	@Inject
	public PolicyRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Policy.class);
	}

}
