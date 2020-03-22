package com.alnt.identity.user.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.identity.user.domain.IdentityUser;
import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

@Singleton
public class IdentityUserRepository extends BaseRepositoryImpl<IdentityUser> implements BaseRepository<IdentityUser> {

	@Inject
	public IdentityUserRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, IdentityUser.class);
	}

}
