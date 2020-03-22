package com.alnt.policyengine.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.policyengine.domain.Currency;
import com.alnt.policyengine.domain.dto.CurrencyDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(CurrencyServiceImpl.class)
public interface CurrencyService extends BaseService<Currency, CurrencyDTO> {

}
