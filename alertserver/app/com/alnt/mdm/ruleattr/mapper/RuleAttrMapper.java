package com.alnt.mdm.ruleattr.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.alnt.mdm.ruleattr.domain.RuleAttr;
import com.alnt.mdm.ruleattr.domain.dto.RuleAttrDTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;

@Mapper(config = MapperCentralConfig.class)
public interface RuleAttrMapper extends BaseMapper<RuleAttrDTO, RuleAttr> {

	public static RuleAttrMapper INSTANCE = Mappers.getMapper(RuleAttrMapper.class);

}
