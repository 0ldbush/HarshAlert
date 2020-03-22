package com.alnt.workflow.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.workflow.domain.Workflow;
import com.alnt.workflow.domain.dto.WorkflowDTO;

@Mapper(config = MapperCentralConfig.class)
public interface WorkflowMapper extends BaseMapper<WorkflowDTO, Workflow> {

	public static WorkflowMapper INSTANCE = Mappers.getMapper(WorkflowMapper.class);


}
