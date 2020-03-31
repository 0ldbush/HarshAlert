package com.alnt.platform.application.logger.service;

import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.base.request.RequestDetails;

public interface Logger extends org.slf4j.Logger{

 void db(RequestDetails requestDetails, AppLogDTO appLog);
}
