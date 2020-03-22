package com.alnt.identity.usergroup.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.identity.usergroup.domain.IdentityUserGroup;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

@Singleton
public class IdentityUserGroupRepository extends BaseRepositoryImpl<IdentityUserGroup> implements BaseRepository<IdentityUserGroup> {

	@Inject
	public IdentityUserGroupRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, IdentityUserGroup.class);
	}

}
