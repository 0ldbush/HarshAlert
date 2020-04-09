package com.alnt.platform.core.messagemaster.service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;

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

    public static ApiMessage getMessage(RequestDetails requestDetails,ApiMessageType apiMessageType, String messageCode, String defaultMessage){
        ApiMessage apiMessage=null;
        String messageDisplayText = StringUtils.isBlank(defaultMessage)?"The operation is successful":defaultMessage;
        String messageText = StringUtils.isBlank(defaultMessage)?"Success":"Success";
        
		try {
			CompletionStage<Page<MessageMaster>> messageMasterResponse=staticMMS.getMessage(requestDetails, apiMessageType, messageCode);
			List<MessageMaster> messageMasterList = messageMasterResponse.toCompletableFuture().get().toList();
	        if(messageMasterList!=null && messageMasterList.size()>0){
	            MessageMaster messageMaster=messageMasterList.get(0);
	            messageText = messageMaster.getText();
	            messageDisplayText = messageMaster.getDescription();      
	        }
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        apiMessage=new ApiMessage();
        apiMessage.setMessageCode(messageCode);
        apiMessage.setMessageType(apiMessageType);
        apiMessage.setMessageDisplayText(messageDisplayText);
        return apiMessage;
    }
    

}