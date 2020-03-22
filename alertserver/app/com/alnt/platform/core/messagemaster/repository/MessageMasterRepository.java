package com.alnt.platform.core.messagemaster.repository;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MessageMasterRepository extends BaseRepositoryImpl<MessageMaster> implements BaseRepository<MessageMaster> {

	@Inject
	public MessageMasterRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MessageMaster.class);
		// TODO Auto-generated constructor stub
	}

	
}
