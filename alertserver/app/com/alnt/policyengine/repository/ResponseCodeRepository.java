package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.ResponseCode;
import com.alnt.policyengine.domain.RuleSet;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class ResponseCodeRepository extends BaseRepositoryImpl<ResponseCode> implements BaseRepository<ResponseCode> {

	@Inject
	public ResponseCodeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, ResponseCode.class);
	}

}
