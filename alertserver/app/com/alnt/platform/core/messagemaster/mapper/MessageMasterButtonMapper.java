package com.alnt.platform.core.messagemaster.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterButtonDTO;

@Mapper(config = MapperCentralConfig.class)
public interface MessageMasterButtonMapper extends BaseMapper<MessageMasterButtonDTO, MessageMasterButton> {

	public static MessageMasterButtonMapper INSTANCE = Mappers.getMapper(MessageMasterButtonMapper.class);
	
}
