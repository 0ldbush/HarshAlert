package com.alnt.mdm.messageType.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.messageType.domain.MessageType;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class MessageTypeRepository extends BaseRepositoryImpl<MessageType> implements BaseRepository<MessageType> {

	@Inject
	public MessageTypeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MessageType.class);
	}

}
