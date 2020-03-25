package com.alnt.platform.core.configsetting.service;

import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import com.alnt.platform.core.configsetting.mapper.ConfigSettingMapper;
import com.alnt.platform.core.configsetting.repository.ConfigSettingRepository;
import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ConfigSettingLocalCachedServiceImpl extends BaseLocalCachedServiceImpl<ConfigSetting, ConfigSettingDTO> implements ConfigSettingService {

    @Inject
    public ConfigSettingLocalCachedServiceImpl(AsyncCacheApi caceApi, HttpExecutionContext ec, ConfigSettingRepository repository) {
        super(caceApi, "ConfigSetting", ec, repository, ConfigSettingMapper.INSTANCE);
    }

}
