package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.MitigationControl;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class MitigationControlRepository extends BaseRepositoryImpl<MitigationControl> implements BaseRepository<MitigationControl> {

	@Inject
	public MitigationControlRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, MitigationControl.class);
	}

}
