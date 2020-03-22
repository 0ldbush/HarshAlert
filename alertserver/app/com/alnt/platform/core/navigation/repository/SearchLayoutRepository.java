package com.alnt.platform.core.navigation.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.navigation.domain.SearchLayout;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class SearchLayoutRepository extends BaseRepositoryImpl<SearchLayout> implements BaseRepository<SearchLayout> {

	@Inject
	public SearchLayoutRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, SearchLayout.class);
	}

}
