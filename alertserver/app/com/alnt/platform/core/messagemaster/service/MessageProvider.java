package com.alnt.platform.core.messagemaster.service;

import com.alnt.platform.base.request.Criteria;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;
import com.alnt.platform.core.messagemaster.domain.dto.MessageMasterDTO;
import com.alnt.platform.core.messagemaster.repository.MessageMasterRepository;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.springframework.data.domain.Page;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Enumeration;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;

@Singleton
public final class MessageProvider {

    private MessageMasterService messageMasterService;

    private static MessageMasterService staticMMS;

    @Inject
    public MessageProvider(MessageMasterService messageMasterService) {
        this.messageMasterService = messageMasterService;
        init(messageMasterService);
    }

    public static void init(MessageMasterService mms) {
        if (staticMMS == null) {
            staticMMS = mms;
        }
    }

    public static ApiMessage getMessage(RequestDetails requestDetails,ApiMessageType apiMessageType, String messageCode) throws ExecutionException, InterruptedException {
        ApiMessage apiMessage=null;
        CompletionStage<Page<MessageMaster>> messageMasterResponse=staticMMS.getMessage(requestDetails, apiMessageType,messageCode);
        List<MessageMaster> messageMasterList = messageMasterResponse.toCompletableFuture().get().toList();
        if(messageMasterList!=null && messageMasterList.size()>0){
            MessageMaster messageMaster=messageMasterResponse.toCompletableFuture().get().toList().get(0);
            apiMessage=new ApiMessage();
            apiMessage.setMessageCode(messageCode);
            apiMessage.setMessageType(apiMessageType);
            apiMessage.setMessageText(messageMaster.getText());
        }
        return apiMessage;
    }
    

}