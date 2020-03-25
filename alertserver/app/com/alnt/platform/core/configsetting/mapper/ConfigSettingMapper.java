package com.alnt.platform.core.configsetting.mapper;

import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.mapper.MapperCentralConfig;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(config = MapperCentralConfig.class)
public interface ConfigSettingMapper extends BaseMapper<ConfigSettingDTO, ConfigSetting> {
    public static ConfigSettingMapper INSTANCE = Mappers.getMapper(ConfigSettingMapper.class);
}
