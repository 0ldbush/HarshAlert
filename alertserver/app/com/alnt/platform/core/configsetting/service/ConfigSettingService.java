package com.alnt.platform.core.configsetting.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ConfigSettingServiceImpl.class)
public interface ConfigSettingService extends BaseService<ConfigSetting, ConfigSettingDTO> {
}
