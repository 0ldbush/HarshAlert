package com.alnt.platform.core.classdef.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.timeline.domain.Timeline;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class ClassDefRepository extends BaseRepositoryImpl<ClassDef> implements BaseRepository<ClassDef> {

	@Inject
	public ClassDefRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, ClassDef.class);
	}

}
