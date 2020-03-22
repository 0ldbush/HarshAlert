package com.alnt.policyengine.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.policyengine.domain.Currency;
import com.alnt.policyengine.domain.dto.CurrencyDTO;
import com.alnt.policyengine.mapper.CurrencyMapper;
import com.alnt.policyengine.repository.CurrencyRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class CurrencyServiceImpl extends BaseServiceImpl<Currency, CurrencyDTO> implements CurrencyService {

	@Inject
	public CurrencyServiceImpl(HttpExecutionContext ec, CurrencyRepository repository) {
		super(ec, repository, CurrencyMapper.INSTANCE);
	}

}
