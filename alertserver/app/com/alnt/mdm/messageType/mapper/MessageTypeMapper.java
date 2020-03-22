package com.alnt.mdm.messageType.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.mdm.messageType.domain.MessageType;
import com.alnt.mdm.messageType.domain.dto.MessageTypeDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface MessageTypeMapper extends BaseMapper<MessageTypeDTO, MessageType> {

	public static MessageTypeMapper INSTANCE = Mappers.getMapper(MessageTypeMapper.class);
	

}
