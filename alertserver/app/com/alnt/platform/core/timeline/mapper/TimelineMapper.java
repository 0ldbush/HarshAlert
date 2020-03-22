package com.alnt.platform.core.timeline.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.timeline.domain.Timeline;
import com.alnt.platform.core.timeline.domain.dto.TimelineDTO;

@Mapper(config = MapperCentralConfig.class)
public interface TimelineMapper extends BaseMapper<TimelineDTO, Timeline> {

	public static TimelineMapper INSTANCE = Mappers.getMapper(TimelineMapper.class);

}
