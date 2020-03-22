package com.alnt.platform.core.messagemaster.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.messagemaster.domain.MessageLog;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class MessageLogRepository extends BaseRepositoryImpl<MessageLog> implements BaseRepository<MessageLog> {

	@Inject
	public MessageLogRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MessageLog.class);
	}

}
