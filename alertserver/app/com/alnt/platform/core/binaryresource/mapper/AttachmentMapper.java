package com.alnt.platform.core.binaryresource.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.binaryresource.domain.Attachment;
import com.alnt.platform.core.binaryresource.domain.dto.AttachmentDTO;

@Mapper(config = MapperCentralConfig.class)
public interface AttachmentMapper extends BaseMapper<AttachmentDTO, Attachment> {

	public static AttachmentMapper INSTANCE = Mappers.getMapper(AttachmentMapper.class);

}
