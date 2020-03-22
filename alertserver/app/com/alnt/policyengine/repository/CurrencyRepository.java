package com.alnt.policyengine.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.persistence.db.jpa.JPAApi;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.repository.BaseRepositoryImpl;
import com.alnt.platform.base.repository.DatabaseExecutionContext;
import com.alnt.policyengine.domain.Currency;

@Singleton
public class CurrencyRepository extends BaseRepositoryImpl<Currency> implements BaseRepository<Currency> {

	@Inject
	public CurrencyRepository(JPAApi jpaApi, DatabaseExecutionContext executionContext) {
		super(jpaApi, executionContext, Currency.class);
	}

}
