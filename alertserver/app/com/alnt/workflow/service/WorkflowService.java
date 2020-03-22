package com.alnt.workflow.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;
import com.alnt.workflow.domain.Workflow;
import com.alnt.workflow.domain.WorkflowStep;
import com.alnt.workflow.domain.dto.GetDocStatusBean;
import com.alnt.workflow.domain.dto.SetDocStatusBean;
import com.alnt.workflow.domain.dto.WorkflowDTO;
import com.alnt.workflow.domain.dto.WorkflowStatusDTO;
import com.alnt.workflow.domain.dto.WorkflowStepDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(WorkflowServiceImpl.class)
public interface WorkflowService extends BaseService<Workflow, WorkflowDTO> {
	
	void createWorkflow(BaseEntity busObj, String workflowCode, UserDTO user);
	
	void createWorkflowById(BaseEntity busObj, String workflowCode, UserDTO user,Long workflowId);
	
	WorkflowStatusDTO setDocStatus(SetDocStatusBean input) throws Exception;
	
	WorkflowStatusDTO getDocStatus(GetDocStatusBean input);
	
	List<WorkflowStepDTO> getWorkflowStepsForUser(UserDTO user, String busObjCat);
	
	List<MessageLogDTO> getWorkflowComments(SetDocStatusBean input) throws Exception;
	
	WorkflowStatusDTO getStatusHistory(SetDocStatusBean input) throws Exception;
	
	Date getEndDateForStep(Long workflowId, Long subId, String stepCode) throws Exception;
	
	Date getStartDateForStep(Long workflowId, Long subId, String stepCode) throws Exception;
	
	Map<String,String> getWorkflowStepCodeText(String busObjCat);
	
	WorkflowStep getEndStep(BaseEntity busObj);
	
	

}
