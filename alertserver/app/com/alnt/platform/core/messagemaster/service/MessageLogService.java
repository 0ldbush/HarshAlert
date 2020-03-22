package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MessageLogServiceImpl.class)
public interface MessageLogService extends BaseService<MessageLog, MessageLogDTO> {

}
