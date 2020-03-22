package com.alnt.platform.core.apiservice.repository;


import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.apiservice.domain.ApiService;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class ApiServiceRepository extends BaseRepositoryImpl<ApiService> implements BaseRepository<ApiService> {

	@Inject
	public ApiServiceRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, ApiService.class);
	}

}
