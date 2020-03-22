package com.alnt.platform.core.messagemaster.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MessageLogMapper extends BaseMapper<MessageLogDTO, MessageLog> {

	public static MessageLogMapper INSTANCE = Mappers.getMapper(MessageLogMapper.class);

}
