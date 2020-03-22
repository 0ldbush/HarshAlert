package com.alnt.platform.core.binaryresource.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.binaryresource.domain.Attachment;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class AttachmentRepository extends BaseRepositoryImpl<Attachment> 
			implements BaseRepository<Attachment> {

	@Inject
	public AttachmentRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Attachment.class);
	}

}
