package com.alnt.access.jobrole.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.access.jobrole.domain.JobRole;
import com.alnt.access.jobrole.domain.dto.JobRoleDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface JobRoleMapper extends BaseMapper<JobRoleDTO, JobRole> {

	public static JobRoleMapper INSTANCE = Mappers.getMapper(JobRoleMapper.class);

}
