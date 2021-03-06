package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.messagemaster.domain.MessageMasterButton;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterButtonDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(MessageMasterButtonServiceImpl.class)
public interface MessageMasterButtonService extends BaseService<MessageMasterButton, MessageMasterButtonDTO> {

}
