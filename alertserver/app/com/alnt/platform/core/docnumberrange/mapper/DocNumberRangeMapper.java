package com.alnt.platform.core.docnumberrange.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.docnumberrange.domain.DocNumberRange;
import com.alnt.platform.core.docnumberrange.domain.dto.DocNumberRangeDTO;

@Mapper(config = MapperCentralConfig.class)
public interface DocNumberRangeMapper extends BaseMapper<DocNumberRangeDTO, DocNumberRange> {

	public static DocNumberRangeMapper INSTANCE = Mappers.getMapper(DocNumberRangeMapper.class);


}
