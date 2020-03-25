package com.alnt.platform.core.configsetting.service;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import com.alnt.platform.core.configsetting.mapper.ConfigSettingMapper;
import com.alnt.platform.core.configsetting.repository.ConfigSettingRepository;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigSettingServiceImpl extends BaseServiceImpl<ConfigSetting, ConfigSettingDTO> implements ConfigSettingService {

    @Inject
    public ConfigSettingServiceImpl(HttpExecutionContext ec, ConfigSettingRepository repository) {
        super( ec, repository, ConfigSettingMapper.INSTANCE);
    }
}
