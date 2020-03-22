package com.alnt.policyengine.controller;

import javax.inject.Inject;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.policyengine.domain.Currency;
import com.alnt.policyengine.domain.dto.CurrencyDTO;
import com.alnt.policyengine.service.CurrencyService;

import play.libs.concurrent.HttpExecutionContext;

public class CurrencyController extends BaseController<Currency, CurrencyDTO> {

	@Inject
	public CurrencyController(CurrencyService currencyService, HttpExecutionContext ec) {
		super(currencyService, ec, Currency.class, CurrencyDTO.class);
	}

}
