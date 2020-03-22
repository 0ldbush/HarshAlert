package com.alnt.mdm.ruleattr.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.ruleattr.domain.RuleAttr;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;

@Singleton
public class RuleAttrRepository extends BaseRepositoryImpl<RuleAttr> implements BaseRepository<RuleAttr> {

	@Inject
	public RuleAttrRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, RuleAttr.class);
	}

}
