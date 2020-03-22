package com.alnt.platform.core.messagemaster.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MessageMasterMapper extends BaseMapper<MessageMasterDTO, MessageMaster> {

	public static MessageMasterMapper INSTANCE = Mappers.getMapper(MessageMasterMapper.class);
	
}
