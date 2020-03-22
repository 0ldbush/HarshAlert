package com.alnt.mdm.attachmentType.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.mdm.attachmentType.domain.AttachmentType;
import com.alnt.mdm.attachmentType.domain.dto.AttachmentTypeDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface AttachmentTypeMapper extends BaseMapper<AttachmentTypeDTO, AttachmentType> {

	public static AttachmentTypeMapper INSTANCE = Mappers.getMapper(AttachmentTypeMapper.class);
	

}
