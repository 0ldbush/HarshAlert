package com.alnt.platform.core.lists.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.lists.domain.Lists;

@Singleton
public class ListsRepository extends BaseRepositoryImpl<Lists> implements BaseRepository<Lists> {

	@Inject
	public ListsRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Lists.class);
	}

}
