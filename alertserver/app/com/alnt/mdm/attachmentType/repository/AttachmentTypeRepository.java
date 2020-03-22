package com.alnt.mdm.attachmentType.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.attachmentType.domain.AttachmentType;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class AttachmentTypeRepository extends BaseRepositoryImpl<AttachmentType> implements BaseRepository<AttachmentType> {

	@Inject
	public AttachmentTypeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, AttachmentType.class);
	}

}
