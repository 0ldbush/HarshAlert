package com.alnt.platform.core.timeline.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.platform.core.timeline.domain.Timeline;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class TimelineRepository extends BaseRepositoryImpl<Timeline> implements BaseRepository<Timeline> {

	@Inject
	public TimelineRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Timeline.class);
	}

}
