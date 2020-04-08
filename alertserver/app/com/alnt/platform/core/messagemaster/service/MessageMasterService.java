package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.google.inject.ImplementedBy;
import org.springframework.data.domain.Page;

import java.util.concurrent.CompletionStage;


@ImplementedBy(MessageMasterServiceImpl.class)
public interface MessageMasterService extends BaseService<MessageMaster, MessageMasterDTO> {
    CompletionStage<Page<MessageMaster>> getMessage(RequestDetails requestDetails, ApiMessageType apiMessageType, String messageCode);
}
