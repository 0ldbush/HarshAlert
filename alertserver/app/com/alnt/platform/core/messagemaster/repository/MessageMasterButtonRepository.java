package com.alnt.platform.core.messagemaster.repository;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class MessageMasterButtonRepository extends BaseRepositoryImpl<MessageMasterButton> implements BaseRepository<MessageMasterButton> {

	@Inject
	public MessageMasterButtonRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MessageMasterButton.class);
		// TODO Auto-generated constructor stub
	}

}
