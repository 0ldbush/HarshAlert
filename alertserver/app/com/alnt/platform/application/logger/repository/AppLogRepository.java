package com.alnt.platform.application.logger.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.application.logger.domain.AppLog;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

@Singleton
public class AppLogRepository extends BaseRepositoryImpl<AppLog> implements BaseRepository<AppLog> {

	@Inject
	public AppLogRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, AppLog.class);
	}

}
