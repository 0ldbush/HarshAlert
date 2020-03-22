package com.alnt.policyengine.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.policyengine.domain.Currency;
import com.alnt.policyengine.domain.dto.CurrencyDTO;

@Mapper(config = MapperCentralConfig.class)
public interface CurrencyMapper extends BaseMapper<CurrencyDTO, Currency> {

	public static CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

}
