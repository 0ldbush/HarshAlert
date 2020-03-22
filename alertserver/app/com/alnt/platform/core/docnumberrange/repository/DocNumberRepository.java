package com.alnt.platform.core.docnumberrange.repository;


import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.docnumberrange.domain.DocNumber;

@Singleton
public class DocNumberRepository extends BaseRepositoryImpl<DocNumber> implements BaseRepository<DocNumber> {

	@Inject
	public DocNumberRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, DocNumber.class);
	}

}
