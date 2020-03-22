package com.alnt.platform.core.timeline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.timeline.domain.TimelineUI;
import com.alnt.platform.core.timeline.domain.dto.TimelineUIDTO;

@Mapper(config = MapperCentralConfig.class)
public interface TimelineUIMapper extends BaseMapper<TimelineUIDTO, TimelineUI> {

	public static TimelineUIMapper INSTANCE = Mappers.getMapper(TimelineUIMapper.class);

}
