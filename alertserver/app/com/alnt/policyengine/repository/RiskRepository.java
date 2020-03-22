package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.Risk;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class RiskRepository extends BaseRepositoryImpl<Risk> implements BaseRepository<Risk> {

	@Inject
	public RiskRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Risk.class);
	}

}
