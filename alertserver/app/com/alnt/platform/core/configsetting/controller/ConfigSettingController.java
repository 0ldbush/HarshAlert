package com.alnt.platform.core.configsetting.controller;

import com.alnt.platform.base.controller.BaseController;
import com.alnt.platform.core.configsetting.domain.ConfigSetting;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import com.alnt.platform.core.configsetting.service.ConfigSettingService;
import com.google.inject.name.Named;
import play.libs.concurrent.HttpExecutionContext;

import javax.inject.Inject;

public class ConfigSettingController extends BaseController<ConfigSetting, ConfigSettingDTO> {

    @Inject
    public ConfigSettingController(ConfigSettingService configSettingService, HttpExecutionContext ec) {
        super(configSettingService, ec, ConfigSetting.class, ConfigSettingDTO.class);
    }
}
