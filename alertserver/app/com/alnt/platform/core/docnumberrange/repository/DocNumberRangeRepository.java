package com.alnt.platform.core.docnumberrange.repository;


import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.docnumberrange.domain.DocNumberRange;

@Singleton
public class DocNumberRangeRepository extends BaseRepositoryImpl<DocNumberRange> implements BaseRepository<DocNumberRange> {

	@Inject
	public DocNumberRangeRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, DocNumberRange.class);
	}

}
