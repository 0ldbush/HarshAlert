package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.ResponseCodeType;

@Singleton
public class ResponseCodeTypeRepository extends BaseRepositoryImpl<ResponseCodeType>
		implements BaseRepository<ResponseCodeType> {

	@Inject
	public ResponseCodeTypeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, ResponseCodeType.class);
	}

}
