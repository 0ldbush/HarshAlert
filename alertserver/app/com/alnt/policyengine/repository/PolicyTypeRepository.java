package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.PolicyType;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class PolicyTypeRepository extends BaseRepositoryImpl<PolicyType> implements BaseRepository<PolicyType> {

	@Inject
	public PolicyTypeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, PolicyType.class);
	}

}
