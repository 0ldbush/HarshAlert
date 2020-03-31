package com.alnt.platform.application.logger.service;

import com.alnt.platform.application.logger.domain.AppLog;
import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(AppLogServiceImpl.class)
public interface AppLogService extends BaseService<AppLog, AppLogDTO> {

}
