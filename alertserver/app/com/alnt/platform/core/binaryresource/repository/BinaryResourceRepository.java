package com.alnt.platform.core.binaryresource.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.binaryresource.domain.BinaryResource;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class BinaryResourceRepository extends BaseRepositoryImpl<BinaryResource> 
			implements BaseRepository<BinaryResource> {

	@Inject
	public BinaryResourceRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, BinaryResource.class);
	}

}
