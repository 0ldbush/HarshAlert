package com.alnt.workflow.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Semaphore;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import com.alnt.access.user.domain.dto.UserDTO;
import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.base.util.PropertyUtil;
import com.alnt.platform.core.apiservice.service.ApiSerService;
import com.alnt.platform.core.messagemaster.domain.MessageButton;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.platform.core.messagemaster.domain.MessageRecipient;
import com.alnt.platform.core.messagemaster.domain.dto.MessageLogDTO;
import com.alnt.platform.core.messagemaster.service.MessageLogService;
import com.alnt.workflow.domain.Workflow;
import com.alnt.workflow.domain.WorkflowCondition;
import com.alnt.workflow.domain.WorkflowConnection;
import com.alnt.workflow.domain.WorkflowRecipient;
import com.alnt.workflow.domain.WorkflowStep;
import com.alnt.workflow.domain.WorkflowStepWorkflowRecipientXref;
import com.alnt.workflow.domain.dto.GetDocStatusBean;
import com.alnt.workflow.domain.dto.InputStatusBean;
import com.alnt.workflow.domain.dto.SetDocStatusBean;
import com.alnt.workflow.domain.dto.WorkflowApiInput;
import com.alnt.workflow.domain.dto.WorkflowDTO;
import com.alnt.workflow.domain.dto.WorkflowStatusDTO;
import com.alnt.workflow.domain.dto.WorkflowStepDTO;
import com.alnt.workflow.mapper.WorkflowMapper;
import com.alnt.workflow.repository.WorkflowRepository;
import com.alnt.workflow.service.type.WorkflowStepParallelTasksType;
import com.alnt.workflow.service.type.WorkflowStepType;
import com.google.common.base.Strings;

import jodd.bean.BeanUtil;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class WorkflowServiceImpl extends BaseServiceImpl<Workflow, WorkflowDTO> implements WorkflowService {
	
	private MessageLogService messageLogService;
	
	private ApiSerService apiSerService;
    
	@Inject
	public WorkflowServiceImpl(HttpExecutionContext ec, WorkflowRepository repository, MessageLogService messageLogService, ApiSerService apiSerService) {
		super( ec, repository, WorkflowMapper.INSTANCE);
		this.messageLogService = messageLogService;
		this.apiSerService = apiSerService;
	}
	
	@Override
	public void createWorkflow(BaseEntity busObj, String workflowCode, UserDTO user) {
		createWorkflowInternal(busObj, workflowCode, user,null);
	}
	@Override
	public void createWorkflowById(BaseEntity busObj, String workflowCode, UserDTO user,Long workflowId) {
		createWorkflowInternal(busObj, workflowCode, user,workflowId);
	}
	
	private void createWorkflowInternal(BaseEntity busObj, String workflowCode, UserDTO user, Long workflowId) {
		Map map = new HashMap();
		String busObjCat = busObj.getClass().getName();
		Workflow newWorkflow = null;
		try {
			
			Workflow templateWorkflow = getTemplateWorkflow(busObjCat, workflowCode, workflowId);

			if(templateWorkflow == null) {
				return;
			}			
			newWorkflow = buildWorkflowWithTemplate(templateWorkflow, busObj);
			
			String firstStepCode = newWorkflow.getFirstStepCode();
			
			newWorkflow = saveSync(newWorkflow);
			
			SetDocStatusBean input = new SetDocStatusBean();
			input.setUser(user);
			input.setBusObjCat(busObjCat);
			input.setBusObjID((Long) BeanUtil.declaredForcedSilent.getProperty(busObj, "id"));
			input.setNextStepExtID(firstStepCode);
			input.setWorkflowID(newWorkflow.getId());
			input.setCalledFromCreateWorkflow(true);
			this.setDocStatus(input);
		} catch (Exception ex) {
			//TODO
			ex.printStackTrace();
		}
	}
	
	private Workflow getTemplateWorkflow(String busObjCat, String workflowCode, Long workflowId) {
		Workflow workflow = null;
		try {			
			List<Workflow> workflows = null;
			if(workflowId!=null){
				Workflow templateWorkflow = getByIdSync(workflowId);
			
				if (templateWorkflow != null && templateWorkflow.getIntStatus() != null
						&& templateWorkflow.getIntStatus().intValue() == 1) {
					workflows = new ArrayList<>();
					workflows.add(templateWorkflow);
				}
				
			}
			if((workflows == null || workflows.isEmpty()) && !Strings.isNullOrEmpty(workflowCode)) {
				CompletionStage<List<Workflow>> workflowGet = ((WorkflowRepository)this.getDaoRepository()).findByWorkflowCodeAndTemplate(workflowCode, true);

				workflows = workflowGet.toCompletableFuture().get();

			}
			if(workflows == null || workflows.isEmpty()) {
				CompletionStage<List<Workflow>> workflowGet = ((WorkflowRepository)this.getDaoRepository()).findByBusObjCatAndTemplate(busObjCat, true);
				workflows = workflowGet.toCompletableFuture().get();
			}
			
			if(workflows == null || workflows.isEmpty()) {
				return null;
			}
			for(Workflow wrk : workflows) {
				if(wrk.getIntStatus() != null && wrk.getIntStatus().intValue() == 1) {
					workflow = wrk;
					break;
				}
			}
			if(workflow == null) {
				return null;
			}

		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
	}
	
	private Workflow buildWorkflowWithTemplate(Workflow templateWorkflow, BaseEntity busObj) {
		
		String busObjCat = busObj.getClass().getName();
		Workflow newWorkflow = new Workflow();
		
		try {
			BeanUtils.copyProperties(newWorkflow, templateWorkflow);

			newWorkflow.setIntStatus(0);
			newWorkflow.setTemplate(false);
			newWorkflow.setId(null);
			newWorkflow.setBusObjTypes(new HashSet<String>());
			
			
			List<WorkflowConnection> newConnections  = new ArrayList<WorkflowConnection>();
			for(WorkflowConnection conn : templateWorkflow.getConnections()) {
				WorkflowConnection newConnection = new WorkflowConnection();
				BeanUtils.copyProperties(newConnection, conn);
				newConnection.setWorkflow(newWorkflow);
				newConnection.setId(null);
				
				List<WorkflowCondition> newConstraints = new ArrayList<>();
				for(WorkflowCondition constraint : conn.getConstraints()) {
					WorkflowCondition newConstraint = new WorkflowCondition();
					BeanUtils.copyProperties(newConstraint, constraint);
					newConstraint.setWorkflowConnection(newConnection);
					newConstraint.setId(null);
					newConstraints.add(newConstraint);
				}
				newConnection.setConstraints(newConstraints);	
				newConnections.add(newConnection);
			}
			newWorkflow.setConnections(newConnections);
			
			List<WorkflowRecipient> newRecipients  = new ArrayList<WorkflowRecipient>();
			for(WorkflowRecipient conn : templateWorkflow.getRecipients()) {
				WorkflowRecipient newRecpc = new WorkflowRecipient();
				BeanUtils.copyProperties(newRecpc, conn);
				newRecpc.setWorkflow(newWorkflow);
				newRecpc.setId(null);
				newRecipients.add(newRecpc);
			}
			newWorkflow.setRecipients(newRecipients);
	
			List<WorkflowStep> newSteps  = new ArrayList<WorkflowStep>();
			for(WorkflowStep step : templateWorkflow.getSteps()) {
				WorkflowStep newStep = new WorkflowStep();
				BeanUtils.copyProperties(newStep, step);
				newStep.setWorkflow(newWorkflow);
				newStep.setId(null);
				newStep.setUsers(new HashSet<String>());
				Set<String> newNotifications = new HashSet<String>();
				for(String notification : step.getNotifications()) {
					newNotifications.add(notification);
				}
				
				Set<String> newAccessRoles = new HashSet<String>();
				for(String notification : step.getAccessRoles()) {
					newAccessRoles.add(notification);
				}
				
				Map<String, String> newAttributes = new HashMap<String, String>();
				for(Entry<String, String> notification : step.getAttributes().entrySet()) {
					newAttributes.put(notification.getKey(), notification.getValue());
				}
				newStep.setNotifications(newNotifications);
				newStep.setReminders(new HashSet<String>());
				newStep.setAccessRoles(newAccessRoles);
				newStep.setFieldValues(new HashMap<String, String>());
				newStep.setAttributes(newAttributes);
				newStep.setMessages(new HashSet<Long>());
				
				List<WorkflowStepWorkflowRecipientXref> newOwners = new ArrayList<>();
				for(WorkflowStepWorkflowRecipientXref xref : step.getOwners()) {
					WorkflowStepWorkflowRecipientXref newXref = new WorkflowStepWorkflowRecipientXref();
					newXref.setId(null);
					newXref.setWorkflowStep(newStep);
					for(WorkflowRecipient newRecp : newWorkflow.getRecipients()) {
						if(newRecp.getWorkflowRecipientCode().equals(xref.getWorkflowRecipient().getWorkflowRecipientCode())) {
							newXref.setWorkflowRecipient(newRecp);
							break;
						}
					}
					newOwners.add(newXref);
				}
				newStep.setOwners(newOwners);
				newSteps.add(newStep);
			}
			newWorkflow.setSteps(newSteps);
			
			//Find the first step of the workflow from lowest workflow.steps.seq where there are no workflow.connections.target = that step. 
			//In other words the lowest sequence step with no predecessors. On this first step set
			
			WorkflowStep firstStep = newWorkflow.getSteps().stream().filter(step -> step.getStartStep()).findFirst().orElse(null);
			newWorkflow.setFirstStepCode(firstStep.getWorkflowStepCode());
			Set<WorkflowStep> probableNextSteps = new HashSet<WorkflowStep>();
			Date date = new Date();
			if (firstStep == null)  {
				Collections.sort(newWorkflow.getSteps(), new Comparator<WorkflowStep>() {
					@Override
					public int compare(WorkflowStep o1, WorkflowStep o2) {
						return (o1.getSeq() - o2.getSeq()) ;
					}
				});
				for(WorkflowStep step : newWorkflow.getSteps()) {
					 boolean targetDefined = false;
					 for(WorkflowConnection conn  : newWorkflow.getConnections()) {
							if(step.getWorkflowStepCode().equals(conn.getTarget())) {
								targetDefined = true;
								break;
							}
					}
					if(!targetDefined) {
						probableNextSteps.add(step);
					}
				}
				firstStep = probableNextSteps.iterator().next();
				
			}
			
			if(firstStep == null) {
				return null ;
			}
			newWorkflow.setBusObjCat(busObjCat);
			newWorkflow.setBusObjId((Long) BeanUtil.declaredForcedSilent.getProperty(busObj, "id"));
		
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newWorkflow;
	}
	
	@Override
	public WorkflowStatusDTO setDocStatus(SetDocStatusBean input) throws Exception{
		return this.setDocStatusInternal(input, true);
	}
	
	public WorkflowStatusDTO setDocStatusInternal(SetDocStatusBean input, boolean lockRequired) throws Exception{
		if (input.getWorkflowID() == null || input.getWorkflowID().longValue() == 0) {
			throw new RuntimeException("Workflow id passed is not valid");
		}
		Workflow workflow = null;
		
		UserDTO user = input.getUser();
		
		Semaphore lock = null;

		WorkflowStep currentStep = null;
		WorkflowStep newStep = null;
		try {
			workflow = getByIdSync(input.getWorkflowID());
			if (workflow == null) {
				throw new RuntimeException("Workflow id passed is not valid");
			}
			if(lockRequired && !input.isCalledFromCreateWorkflow()) {
				//getEntityManagerLock(workflow, LockModeType.PESSIMISTIC_WRITE);
				//workflowDao.getEntityManager().refresh(workflow);
			}
			if(Strings.isNullOrEmpty(input.getBusObjCat())) {
				input.setBusObjCat(workflow.getBusObjCat());
			}
			if(input.getBusObjID() == null) {
				input.setBusObjID(workflow.getBusObjId());
			}
			
			Object busObj = this.getGenericObjectByIdSync(Class.forName(workflow.getBusObjCat()), input.getBusObjID());
		
			for(WorkflowStep step : workflow.getSteps()) {  
				if(step.getWorkflowStepCode().equalsIgnoreCase(input.getSourceRefID()) && step.getActualEnd() == null) {
					if(Strings.isNullOrEmpty(step.getParallelField()) || (step.getParallelId() != null && input.getSubID() != null && step.getParallelId().longValue() == input.getSubID().longValue())) {
						currentStep = step;
					}
				}
				if(step.getWorkflowStepCode().equalsIgnoreCase(input.getNextStepExtID()) && step.getParallelId() == null) {
					newStep = step;
				}
			}
			if(currentStep == null && (newStep.getStartStep() == null || newStep.getStartStep().booleanValue() == false)) {
				//throw new WorkflowException(ErrorType.WORKFLOW_ALREADY_COMPLETED);
				throw new Exception("Workflow already completed");
			}
//			if(newStep != null && newStep.getActualEnd() != null) {
//				throw new WorkflowException(ErrorType.WORKFLOW_ALREADY_COMPLETED);
//			}
			//check if connection exist  from source to next 
			//TODO remove ORDER_WORKFLOW condition later
			if(currentStep != null && newStep != null && !("ORDER_WORKFLOW".equalsIgnoreCase(workflow.getWorkflowCode()))) {
				boolean found = false;
				for(WorkflowConnection conn : workflow.getConnections()) {
					if(conn.getSource().equalsIgnoreCase(currentStep.getWorkflowStepCode()) && conn.getTarget().equalsIgnoreCase(newStep.getWorkflowStepCode())) {
						found = true;
						break;
					}
				}
				if(!found) {
					//throw new WorkflowException(ErrorType.WORKFLOW_NO_CONNECTION);
					throw new Exception("Workflow have no connections");
				}
				
			}
			
			markStepComplete(currentStep, workflow, user, busObj, input, newStep);
			
			if(newStep != null && newStep.getParallelTasks() == null) {
				this.doNextStepProcessing(currentStep, newStep, workflow, input, user, busObj, false);
			}
				
			List<WorkflowStep> newParallelStpes   = new ArrayList<WorkflowStep>();
			newParallelStpes.add(newStep);
			if(newStep != null && newStep.getParallelTasks() != null) {
				if(WorkflowStepParallelTasksType.SUBDOC.getType().equals(newStep.getParallelTasks().getType())) {
					Object subDocs  = BeanUtil.declaredForcedSilent.getProperty(busObj, StringUtils.substringBefore(newStep.getParallelField(), "."));
					String afterString  = StringUtils.substringAfter(newStep.getParallelField(), ".");
					if(subDocs instanceof List) {
						for(Object o : (List)subDocs) {
							if(o != null) {
								Long subID = BeanUtil.declaredForcedSilent.getProperty(o, "id");
								if(input.getSubID() == null || subID.longValue() == input.getSubID().longValue()) {
									WorkflowStep aStep = new WorkflowStep();
									PropertyUtils.copyProperties(aStep, newStep);
	
									aStep.setId(null);
									aStep.setUsers(new HashSet<String>());
									aStep.setReminders(new HashSet<String>());
									aStep.setAccessRoles(new HashSet<String>());
									aStep.setFieldValues(new HashMap<String, String>());
									aStep.setAttributes(new HashMap<String, String>());
									aStep.setMessages(new HashSet<Long>());
									
									Set<String> newNotifications = new HashSet<String>();
									for(String notification : newStep.getNotifications()) {
										newNotifications.add(notification);
									}
									aStep.setNotifications(newNotifications);
									List<WorkflowStepWorkflowRecipientXref> newOwners = new ArrayList<>();
									for(WorkflowStepWorkflowRecipientXref xref : newStep.getOwners()) {
										WorkflowStepWorkflowRecipientXref newXref = new WorkflowStepWorkflowRecipientXref();
										newXref.setId(null);
										newXref.setWorkflowStep(aStep);
										for(WorkflowRecipient newRecp : workflow.getRecipients()) {
											if(newRecp.getWorkflowRecipientCode().equals(xref.getWorkflowRecipient().getWorkflowRecipientCode())) {
												newXref.setWorkflowRecipient(newRecp);
												break;
											}
										}
										newOwners.add(newXref);
									}
									aStep.setOwners(newOwners);
									
//									getRecipientsForStep(aStep, workflow, input, user, busObj);
									
									this.doNextStepProcessing(currentStep, aStep, workflow, input, user, busObj, false);
									aStep.setGenerated(true);
									aStep.setParallelId(subID);
									newParallelStpes.add(aStep);
									
									BeanUtil.declaredForcedSilent.setProperty(o, "workflowStatus", input.getNextStepExtID());
									BeanUtil.declaredForcedSilent.setProperty(o, "workflowId", workflow.getId());
									if(newStep.getSystemStatus() != null) {
										BeanUtil.declaredForcedSilent.setProperty(busObj, "intStatus", newStep.getSystemStatus().intValue());
									}
									
									 if(!Strings.isNullOrEmpty(newStep.getBusObjCatStatus()))
									    BeanUtil.declaredForcedSilent.setProperty(o, "status", newStep.getBusObjCatStatus());

									
									workflow.getSteps().add(aStep);
							
								}
							}
						}
					}
				} else if(WorkflowStepParallelTasksType.OWNER.getType().equals(newStep.getParallelTasks().getType())) {
					if(newStep.getUsers().size() > 1) {
						String firstUser = null;
						for(String userName : newStep.getUsers()) {
							if(firstUser == null) {
								firstUser = userName;
								continue;
							}
							WorkflowStep aStep = new WorkflowStep();
							BeanUtils.copyProperties(aStep, newStep);
							aStep.setUsers(new HashSet<String>());
							aStep.getUsers().add(userName);
							aStep.setGenerated(true);
							newParallelStpes.add(aStep);
						}
						HashSet<String> newUser = new HashSet<String>();
						newUser.add(firstUser);
						newStep.setUsers(newUser);
					}
				} else if(WorkflowStepParallelTasksType.SUCCESSOR.getType().equals(newStep.getParallelTasks().getType())) {
					
				}
			} 
			
			
		if(!Strings.isNullOrEmpty(newStep.getEntryApi())) {
			Arrays.stream(newStep.getEntryApi().split(",")).forEach(api->{invokeApi(api, busObj, user, input);});
			}
			
			//send notification
			for(WorkflowStep step : newParallelStpes) {
				if(step.getParallelTasks() != null && step.getParallelId() == null) {
					continue;
				}
				Map<String, String> fieldValuesNoti = getFieldValues(busObj,step);
				if(!Strings.isNullOrEmpty(step.getNotificationMessagesgCode())) {
					
					List<String> notificationUsers = resolveRecipientRecipientsForStep(new ArrayList<String>(newStep.getNotifications()), newStep, workflow, input, user, busObj);
					
					//notificationPublisher.buildAndPublishNotification(step.getNotificationMessagesgCode(), fieldValuesNoti, notificationUsers, user);
				}
				if(!Strings.isNullOrEmpty(step.getActionMessageCode())) {
					MessageLog actionMessage = new MessageLog();
					Map<String, String> fieldValues = new HashMap<String, String>();
					fieldValues.put("statusID", step.getWorkflowStepCode());
					fieldValues.put("preferredText", step.getText());
					fieldValues.put("busObjCat", busObj.getClass().getName());
					fieldValues.put("busObjCatText", busObj.getClass().getSimpleName());
					fieldValues.put("busObjID", BeanUtil.declaredForcedSilent.getProperty(busObj, "id") != null ? BeanUtil.declaredForcedSilent.getProperty(busObj, "id").toString() : null);
					fieldValues.put("workflowID", workflow.getId().toString());
					fieldValues.put("sourceRefID", currentStep != null ? currentStep.getWorkflowStepCode() : null);
					
					List<WorkflowStep> nextSteps = this.findNextSteps(Arrays.asList(step), workflow, busObj, input);			
					List<MessageButton> buttons = getMessageLogButtons(step,null, workflow, busObj, nextSteps, actionMessage);
					
					actionMessage.setFieldValues(fieldValues);
					actionMessage.setButtons(buttons);
					//actionMessage.setType(MessageType.WORKFLOW.getType());
					actionMessage.setType("WORKFLOW");
					//actionMessage.setMessageMasterCode(step.getActionMessageCode());
					//actionMessage.setPublishedOn(new Date());
					//actionMessage.setPublishedByUserId(user.getId());
					//actionMessage.setPublishedByCustomerId(user.getCustomerId());
					for(String userName : step.getUsers()) {
						MessageRecipient recipient = new MessageRecipient();
						//recipient.setUserName(userName);
						recipient.setMessageLogId(actionMessage.getId());
						actionMessage.getRecipients().add(recipient);
					}
					actionMessage = saveMessageLogSync(actionMessage);
					step.getMessages().add(actionMessage.getId());
					if(step.getUsers() != null && step.getUsers().size() > 0) {
						List<String> notificationUsers = new ArrayList<String>();
						for(String stepUser:step.getUsers()){
							if(!stepUser.startsWith("$"))
							 notificationUsers.add(stepUser);
						}
						//if(notificationUsers.size()>0)
						//notificationPublisher.buildAndPublishNotification(step.getActionMessageCode(), fieldValuesNoti, notificationUsers, user);
					}
				}
			}
			
			
			if(newParallelStpes.size() == 1) {
				List<WorkflowStep> nextSteps = this.findNextSteps(newParallelStpes, workflow, busObj, input);
				if(nextSteps == null || nextSteps.isEmpty()) {
					// mark this step as completed as well if no next step.. i.e last steps
					markStepComplete(newParallelStpes.get(0), workflow, user, busObj, input, null);
				}
			}
			workflow = saveSync(workflow);
			
			if(input.getSubID() != null && input.getSubID().longValue() > 0) {
				String currentDBStatus = BeanUtil.declaredSilent.getProperty(busObj, "workflowStatus");
				if(StringUtils.isNotBlank(currentDBStatus)) {
					Integer currentDBSeq = null;
					for(WorkflowStep step : workflow.getSteps()) {  
						if(step.getWorkflowStepCode().equalsIgnoreCase(currentDBStatus)) {
							currentDBSeq = step.getSeq();
							break;
						}
					}
					
					if(!Strings.isNullOrEmpty(newStep.getParallelField())) {
						Object subDocs  = subDocs = BeanUtil.declaredSilent.getProperty(busObj, StringUtils.substringBefore(newStep.getParallelField(), "."));
						String afterString  = StringUtils.substringAfter(newStep.getParallelField(), ".");
						if(((List)subDocs).size() <= 1) {
							BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowStatus", input.getNextStepExtID());
						} else {
							if(subDocs != null && subDocs instanceof List) {
								String lowestSeqStepCode = null;
								Integer lowestSeqSeq = null;
								for(Object o : (List)subDocs) {
									if(o != null) {
										String childDBStatus = BeanUtil.declaredSilent.getProperty(o, "workflowStatus");
										if(!Strings.isNullOrEmpty(childDBStatus)) {
											WorkflowStep childDBStep = null;
											for(WorkflowStep step : workflow.getSteps()) {  
												if(step.getWorkflowStepCode().equalsIgnoreCase(childDBStatus)) {
													childDBStep = step;
													break;
												}
											}
											if(childDBStep != null && childDBStep.getSeq() != null) {
												if(lowestSeqStepCode ==null || childDBStep.getSeq().intValue() < lowestSeqSeq.intValue()) {
													lowestSeqStepCode = childDBStep.getWorkflowStepCode();
													lowestSeqSeq = childDBStep.getSeq();
												}
											}
											
										}
									}
								}
								if(!Strings.isNullOrEmpty(lowestSeqStepCode)) {
									BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowStatus", lowestSeqStepCode);
								}
							}
						}
					} else {
						BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowStatus", input.getNextStepExtID());
					}
				} else {
					BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowStatus", input.getNextStepExtID());
				}
			} else {
				BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowStatus", input.getNextStepExtID());
			}
			BeanUtil.declaredForcedSilent.setProperty(busObj, "workflowId", workflow.getId());
			if(newStep.getSystemStatus() != null) {
				BeanUtil.declaredForcedSilent.setProperty(busObj, "intStatus", newStep.getSystemStatus().intValue());
			}
			if(!Strings.isNullOrEmpty(newStep.getBusObjCatStatus())) {
				Field field = busObj.getClass().getDeclaredField("status");
				if(field != null) {
					field.setAccessible(true);
					field.set(busObj, newStep.getBusObjCatStatus());
				}
			}
			//genericEntityDao.save(busObj);
			this.saveGenericSync(busObj);

			

			if(WorkflowStepType.SYSTEM.getType().equals(newStep.getType().getType())) {
				//auto Step, we need to move ahead
				List<WorkflowStep> newNextSteps = findNextSteps(Arrays.asList(newStep), workflow, busObj, input);
				if(newNextSteps != null && newNextSteps.size() == 1) {
					SetDocStatusBean setDocStatusBean = new SetDocStatusBean();
					setDocStatusBean.setUser(input.getUser());
					setDocStatusBean.setWorkflowID(workflow.getId());
					setDocStatusBean.setBusObjCat(workflow.getBusObjCat());
					setDocStatusBean.setBusObjID(workflow.getBusObjId());
					setDocStatusBean.setSubID(input.getSubID());
					setDocStatusBean.setSourceRefID(newStep.getWorkflowStepCode());;
					setDocStatusBean.setNextStepExtID(newNextSteps.get(0).getWorkflowStepCode());;
					return this.setDocStatusInternal(setDocStatusBean, false);
				} else if(newNextSteps != null && newNextSteps.size() != 1) {
					throw new RuntimeException("Multiple Parallel steps found instead of 1");
				}
			}
			
		} finally {
			if(lock != null) {
				lock.release();
			}
		}
		GetDocStatusBean inputToGetDocStatus = new GetDocStatusBean();
		inputToGetDocStatus.setWorkflowID(workflow.getId());
		inputToGetDocStatus.setUser(user);
		inputToGetDocStatus.setBusObjCat(workflow.getBusObjCat());
		inputToGetDocStatus.setBusObjID(workflow.getBusObjId());
		WorkflowStatusDTO result = this.getDocStatus(inputToGetDocStatus);
		return result;
	}
	
	@Override
	public WorkflowStatusDTO getDocStatus(GetDocStatusBean input) {
		WorkflowStatusDTO result = new WorkflowStatusDTO();
		List<WorkflowStep> finalNextSteps = new ArrayList<WorkflowStep>();

		if (input.getWorkflowID() == null || input.getWorkflowID().longValue() == 0) {
			return result;
		}

		Workflow workflow = getByIdSync(input.getWorkflowID());

		if (workflow == null) {
			throw new RuntimeException("Workflow id passed is not valid");
		}
		if(Strings.isNullOrEmpty(input.getBusObjCat())) {
			input.setBusObjCat(workflow.getBusObjCat());
		}
		if(input.getBusObjID() == null) {
			input.setBusObjID(workflow.getBusObjId());
		}

		UserDTO user = input.getUser();

		// get current step
		List<WorkflowStep> qualifiedForCurrentSteps = new ArrayList<WorkflowStep>();
		List<WorkflowStep> currentUserCurrentSteps = new ArrayList<WorkflowStep>();
		
		
		for (WorkflowStep step : workflow.getSteps()) {
			if(input.getSubID() != null && input.getSubID() >= 0) {
				if (step.getActualStart() != null && step.getParallelId() != null && step.getParallelId().longValue() == input.getSubID().longValue()
						&& (step.getActualEnd() == null || step.getCompletedById() == null || step.getCompletedById() <= 0)
						&& (WorkflowStepType.APPROVAL.equals(step.getType())
								|| WorkflowStepType.DATA.equals(step.getType()))) {
					qualifiedForCurrentSteps.add(step);
				}
			} else {
				if (step.getActualStart() != null
						&& (step.getActualEnd() == null || step.getCompletedById() == null || step.getCompletedById() <= 0)
						&& (WorkflowStepType.APPROVAL.equals(step.getType())
								|| WorkflowStepType.DATA.equals(step.getType()))) {
					if(Strings.isNullOrEmpty(step.getParallelField()) || (step.getParallelId() != null && input.getSubID() != null && step.getParallelId().longValue() == input.getSubID().longValue())) {
						qualifiedForCurrentSteps.add(step);
					}
				}
			}
		}

		if (qualifiedForCurrentSteps.size() > 0) { // multiple parallel steps
			for (WorkflowStep step : qualifiedForCurrentSteps) {
				if ((step.getUsers().isEmpty() && (step.getOwners() == null || step.getOwners().isEmpty()))
						|| step.getUsers().contains(user.getUserName()) /*
																		 * || UserDTO.isRoleExists(user.getUserRoles(),
																		 * RoleType.ROLE_ADMIN.getType())
																		 */) {
					currentUserCurrentSteps.add(step);
				}
			}
		}
		Collections.sort(currentUserCurrentSteps, new Comparator<WorkflowStep>() {
			@Override
			public int compare(WorkflowStep o1, WorkflowStep o2) {
				return (o1.getSeq() - o2.getSeq());
			}
		});
		Collections.sort(qualifiedForCurrentSteps, new Comparator<WorkflowStep>() {
			@Override
			public int compare(WorkflowStep o1, WorkflowStep o2) {
				return (o1.getSeq() - o2.getSeq());
			}
		});

		Object busObj = null;
		
		try {
			busObj = this.getGenericObjectByIdSync(Class.forName(workflow.getBusObjCat()), input.getBusObjID());
		} catch(Exception ex) {
			//TODO
			ex.printStackTrace();
			//LOG.error(ex);
		}
		
		WorkflowStep currentStep = currentUserCurrentSteps.isEmpty() ? (qualifiedForCurrentSteps.isEmpty() ? null : qualifiedForCurrentSteps.get(0)) : currentUserCurrentSteps.get(0);
		if(currentStep == null) {
			//all step closed.. so just read from document
			if(Strings.isNullOrEmpty(input.getBusObjCat())) {
				input.setBusObjCat(workflow.getBusObjCat());
			}
			if(input.getBusObjID() == null) {
				input.setBusObjID(workflow.getBusObjId());
			}
			try {
				String statusFromDoc = BeanUtil.declaredForcedSilent.getProperty(busObj, "workflowStatus");
				if(!Strings.isNullOrEmpty(statusFromDoc)) {
					for (WorkflowStep step : workflow.getSteps()) {
						if (statusFromDoc.equals(step.getWorkflowStepCode())) {
							currentStep = step;
						}
					}
				}
			} catch(Exception ex) {
				//TODO
				ex.printStackTrace();
				//LOG.error(ex);
			}
		} else if(!currentUserCurrentSteps.isEmpty()) {
			if (workflow.getNoConnections()) {
				for (WorkflowStep step : workflow.getSteps()) {
					if (step.getSeq() != currentStep.getSeq() && step.getCompletedById() == null
							&& (WorkflowStepType.APPROVAL.equals(step.getType())
									|| WorkflowStepType.APPROVAL.equals(step.getType()))) {
						finalNextSteps.add(step);
					}
				}
		
			} else {
				finalNextSteps = this.findNextSteps(currentUserCurrentSteps, workflow, busObj, input);
				if (WorkflowStepParallelTasksType.SUCCESSOR.equals(currentStep.getParallelTasks())) {
					Collections.sort(finalNextSteps, new Comparator<WorkflowStep>() {
						@Override
						public int compare(WorkflowStep o1, WorkflowStep o2) {
							return (o1.getSeq() - o2.getSeq());
						}
					});
					finalNextSteps = Arrays.asList(finalNextSteps.get(0));
				}
			}
		}

		if(!finalNextSteps.isEmpty()) 
			Collections.sort(finalNextSteps, new Comparator<WorkflowStep>() {
				@Override
				public int compare(WorkflowStep o1, WorkflowStep o2) {
					return (o1.getSeq() - o2.getSeq());
				}
			});
		result.setNextState(convertStepTOState(finalNextSteps, result));
		result.setPrimaryState(convertStepTOState(currentStep, result));
		result.setWorkflowID(input.getWorkflowID());

		return result;
	}
	
	private List<WorkflowStep> findNextSteps(List<WorkflowStep> steps, Workflow workflow, Object busObj, InputStatusBean input){
		List<WorkflowStep> nextSteps = new ArrayList<WorkflowStep>();
		for(WorkflowStep step : steps) {
			for (WorkflowConnection conn : workflow.getConnections()) {
				if (step.getWorkflowStepCode().equalsIgnoreCase(conn.getSource())) {
					for (WorkflowStep step2 : workflow.getSteps()) {
//						if(WorkflowStepType.SYSTEM.equals(step2.getType())) {
//							nextSteps.addAll(findNextSteps(Arrays.asList(step2), workflow));
//						} else {
							if (step2.getWorkflowStepCode().equalsIgnoreCase(conn.getTarget())) {
								if(checkWorkflowConnectionCriteria(step, step2, conn, workflow, busObj, input)) {
									if(!Strings.isNullOrEmpty(conn.getResponse())) {
										step2.setLabel(conn.getResponse());
									}
									nextSteps.add(step2);
									break;
								}
							}
//						}
					}
				}
			}
		}
		return nextSteps;
	}
	
	private boolean checkWorkflowConnectionCriteria(WorkflowStep sourceStep, WorkflowStep targetStep, WorkflowConnection connection, 
			Workflow workflow, Object busObj, InputStatusBean input) {
		boolean passed = false;
		if(connection.getConstraints() == null || connection.getConstraints().isEmpty()) {
			passed = true;
		} else {
			//TODO as of now we doing only API, will implement the rest later
			boolean allResult = true,isFirst=true;
			Map<String, Object> params = new HashMap<>();
			params.put("workflowStep",  sourceStep);
			params.put("document", busObj);
			params.put("input", input);
			for(WorkflowCondition condition : connection.getConstraints() ) {
				
				if(!Strings.isNullOrEmpty(condition.getConditionApi())) {
					Object result = apiSerService.invokeApi(condition.getConditionApi(), params);
					if(result != null && result instanceof Boolean && ((Boolean)result).booleanValue()) {
						allResult = condition.getIsOr() ? allResult || true : allResult && true;
					} else {
						allResult = condition.getIsOr() ? allResult || false :allResult && false;
					}
				} else if(!Strings.isNullOrEmpty(condition.getFieldName()) && !Strings.isNullOrEmpty(condition.getFieldName())
						&& !Strings.isNullOrEmpty(condition.getValue()))
				{
//					Object value = 	BeanUtil.declaredForcedSilent.getProperty(busObj, condition.getFieldName());
					
					Object value=null;
					try {
						value = PropertyUtil.getPropertyValue(busObj, condition.getFieldName());
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String constantValue=condition.getValue();
					if(value instanceof HashSet)
					{
						Set valueSet=(Set)value;
						List<String> valueStr = new ArrayList<>(valueSet);
						
						if(condition.getOperator().equals("=") ){
							if(condition.getIsOr())
							{
								if(isFirst)
							allResult=	allResult && valueStr.contains(constantValue);
								else {allResult=	allResult || valueStr.contains(constantValue); isFirst=false;}
							}
							else allResult=	allResult && valueStr.contains(constantValue);	
							
						}else if(condition.getOperator().equals("!=") ){
							if(condition.getIsOr())
							{
								if(isFirst)
								allResult=	allResult && !valueStr.contains(constantValue);	
								else {allResult=	allResult || !valueStr.contains(constantValue); isFirst=false;}	
							}
							else allResult=	allResult && !valueStr.contains(constantValue);	
							
						}else if(condition.getOperator().equals("NOT_IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && Collections.disjoint(constantValueList,valueStr);	
								else {allResult=	allResult || Collections.disjoint(constantValueList,valueStr);	isFirst=false;}	
							}
							else allResult=allResult && Collections.disjoint(constantValueList,valueStr);
						}else if(condition.getOperator().equals("IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && !Collections.disjoint(constantValueList,valueStr);	
								else {allResult=	allResult || !Collections.disjoint(constantValueList,valueStr);	isFirst=false;}	
							}
							else allResult=allResult && !Collections.disjoint(constantValueList,valueStr);
						}
					}
					else if(value instanceof String)
					{
						String valueStr=(String)value;
						if(condition.getOperator().equals("=") )
						{
							if(condition.getIsOr())
							{
								if(isFirst)
							allResult=	allResult && valueStr.equals(constantValue);
								else {allResult=	allResult || valueStr.equals(constantValue); isFirst=false;}
							}
							else allResult=	allResult && valueStr.equals(constantValue);	
							
						}else if(condition.getOperator().equals("!=") )
						{
							if(condition.getIsOr())
							{
								if(isFirst)
								allResult=	allResult && !valueStr.equals(constantValue);	
								else {allResult=	allResult || !valueStr.equals(constantValue); isFirst=false;}	
							}
							else allResult=	allResult && !valueStr.equals(constantValue);	
							
						}else if(condition.getOperator().equals("NOT_IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && !constantValueList.contains(valueStr);	
								else {allResult=	allResult || !constantValueList.contains(valueStr);	isFirst=false;}	
							}
							else allResult=allResult && !constantValueList.contains(valueStr);
						}else if(condition.getOperator().equals("IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && constantValueList.contains(valueStr);	
								else {allResult=	allResult || constantValueList.contains(valueStr);	isFirst=false;}	
							}
							else allResult=allResult && constantValueList.contains(valueStr);
						}
					}else if(value instanceof Long)
					{
						Long valueNumber=(Long)value;
						
						if(condition.getOperator().equals("=") )
						{
							Long constantValueNumber=Long.parseLong(constantValue);
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=	allResult && valueNumber==constantValueNumber;	
								else {allResult=	allResult || valueNumber==constantValueNumber;	isFirst=false;}	
							}
							else allResult=	allResult && valueNumber==constantValueNumber;	
							
						}else if(condition.getOperator().equals("!=") )
						{
							Long constantValueNumber=Long.parseLong(constantValue);
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=	allResult && valueNumber!=constantValueNumber;	
								else {allResult=	allResult || valueNumber!=constantValueNumber;	isFirst=false;}	
							}
							else allResult=	allResult && valueNumber!=constantValueNumber;	
							
						}else if(condition.getOperator().equals("NOT_IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && !constantValueList.contains(valueNumber.toString());	
								else {allResult=	allResult || !constantValueList.contains(valueNumber.toString());	isFirst=false;}	
							}
							else allResult=allResult && !constantValueList.contains(valueNumber.toString());
						}else if(condition.getOperator().equals("IN") )
						{
							List<String> constantValueList= Arrays.asList(constantValue.split(","));;
							
							if(condition.getIsOr())
							{
								if(isFirst)
									allResult=allResult && constantValueList.contains(valueNumber.toString());	
								else {allResult=	allResult || constantValueList.contains(valueNumber.toString());	isFirst=false;}	
							}
							else allResult=allResult && constantValueList.contains(valueNumber.toString());
						}
					}
					
				}
			}
			passed = allResult;
		}
		
		return passed;
	}
	
	
	private void markStepComplete(WorkflowStep step, Workflow workflow, UserDTO user, Object busObj, InputStatusBean input, WorkflowStep newStep) {
		if(step != null) {
			if(step.getUsers() != null && !step.getUsers().isEmpty()) {
				if((user == null || (!step.getUsers().contains(user.getUserName()))) && (newStep == null || (newStep.getOwners() != null && !newStep.getOwners().isEmpty()))) {
					//throw new WorkflowException(ErrorType.WORKFLOW_NO_AUTHORIZATION);
				}
			}
			step.setActualEnd(new Date());
			step.setCompletedById(user.getId());
			
			if(step.getMessages() != null && !step.getMessages().isEmpty()) {
				for(Long messageLogID : step.getMessages()) {
					MessageLog messageLog = (MessageLog)getGenericObjectByIdSync(MessageLog.class, messageLogID);
					if(messageLog != null) {
						MessageButton actionButton = null;
						for (MessageButton btn : messageLog.getButtons()) {
							for (Map.Entry<String, String> entry : btn.getFieldValues().entrySet()) {
								if ("nextStepID".equals(entry.getKey())) {
									actionButton = btn;
									break;
								}
							}
							if(actionButton != null) {
								break;
							}
						}
						if (messageLog.getRecipients() != null) {
							for (MessageRecipient recp : messageLog.getRecipients()) {
								if (user.getUserName() != null && user.getId().equals(recp.getUserId())) {
									recp.setRespondedOn(new Date());
									recp.setReadOn(new Date());
									recp.setScoreResponse(actionButton.getSeq());
								}
							}
							//messageLog.setResponded(Boolean.TRUE);
						}
					}
					//if(messageLog.getResponded() != null && messageLog.getResponded().booleanValue()) {
						//messageLogService.save(messageLog);
					//}
					this.saveMessageLogSync(messageLog);
				}
			}
			if(!Strings.isNullOrEmpty(step.getExitApi())) {
				Arrays.stream(step.getExitApi().split(",")).forEach(api->{invokeApi(api, busObj, user, input);});
			}
		}
	}
	
	private Object invokeApi(String apiName, Object busObj, UserDTO user, InputStatusBean input) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("document", busObj);
		params.put("user", user);
		params.put("documentId", BeanUtil.declaredForcedSilent.getProperty(busObj, "id"));
		params.put("userName", user.getUserName());
		
		WorkflowApiInput inputDTO = new WorkflowApiInput();
		inputDTO.setBusObj(busObj);
		inputDTO.setUser(user);
		inputDTO.setSubID(input.getSubID());
		inputDTO.setInput(input);
		
		params.put("inputDTO", inputDTO);
		return apiSerService.invokeApi(apiName, params);
	}
	
	private List<MessageButton> getMessageLogButtons(WorkflowStep newStep, WorkflowStep currentStep, Workflow wf,Object busObj, List<WorkflowStep> nextSteps, MessageLog actionMessage) {
		
		List<MessageButton> buttons = new ArrayList<MessageButton>();
		if(nextSteps != null) {
			for(WorkflowStep s : nextSteps) {
				MessageButton btn = new MessageButton();
				btn.setClickableText(s.getText());
				btn.setClickedText(s.getText());
				btn.getFieldValues().put("nextStepID", s.getWorkflowStepCode());
				btn.setMessageLogId(actionMessage.getId());
				buttons.add(btn);
			}
			
		}
		return buttons;
	}
	private void doNextStepProcessing(WorkflowStep currentStep, WorkflowStep newStep, Workflow workflow, SetDocStatusBean input, UserDTO user, Object busObj, boolean executeEntryApi) throws Exception{
		boolean isNextStepActive = false;
		if(newStep.getParallelCloseCount() != null && newStep.getParallelCloseCount().intValue() > 1){
			if(newStep.getCurrentCloseCount() == null && newStep.getCurrentCloseCount().intValue() == 0){
				newStep.setCurrentCloseCount(newStep.getParallelCloseCount()-1);
			} else if(newStep.getCurrentCloseCount() != null && newStep.getCurrentCloseCount().intValue() > 1) {
				newStep.setCurrentCloseCount(newStep.getCurrentCloseCount()-1);
			} else if(newStep.getCurrentCloseCount() != null && newStep.getCurrentCloseCount().intValue() == 1) { 
				newStep.setCurrentCloseCount(0);
				isNextStepActive = true;
			}
		} else {
			isNextStepActive = true;
		}
		if(isNextStepActive) {
			if(newStep.getPlannedStart() == null) {
				newStep.setPlannedStart(new Date());
			}
			newStep.setActualStart(new Date());
			if(!Strings.isNullOrEmpty(input.getRemark())) {
				newStep.setPriorStepComments(input.getRemark());
			}
			newStep.setPreviousStep(input.getSourceRefID());
			if(newStep.getLeadTime() != null && newStep.getLeadTime() > 0) {
				newStep.setPlannedEnd(new Date(newStep.getActualStart().getTime()  + newStep.getLeadTime()));
			}
			newStep.setRepeat((newStep.getRepeat() == null ? 0 : newStep.getRepeat())+1);
			newStep.setActualEnd(null);
			newStep.setCompletedById(null);
			
			//find and close all previous steps
			List<WorkflowStep> openPreviousSteps = new ArrayList<WorkflowStep>();
			for(WorkflowConnection connection : workflow.getConnections()) {
				if(newStep.getWorkflowStepCode().equals(connection.getTarget())) {
					for(WorkflowStep step : workflow.getSteps()) {
						if(step.getWorkflowStepCode().equals(connection.getSource())) {
							if(Strings.isNullOrEmpty(step.getParallelField()) || (step.getParallelId() != null && input.getSubID() != null && step.getParallelId().longValue() == input.getSubID().longValue())) {
								
								if(step.getActualEnd() == null && step.getActualStart() != null) {
									openPreviousSteps.add(step);
								}
							}
						}
					}
				}
			}
			for(WorkflowStep openPreviousStep : openPreviousSteps) {
				markStepComplete(openPreviousStep, workflow, user, busObj, input, newStep);
				
			}

			if(executeEntryApi) {
				if(!Strings.isNullOrEmpty(newStep.getEntryApi())) {
					Arrays.stream(newStep.getEntryApi().split(",")).forEach(api->{invokeApi(api, busObj, user, input);});
					
				}
			}
			getRecipientsForStep(newStep, workflow, input, user, busObj);
		}
	}
	
	private List<String> resolveRecipientRecipientsForStep(List<String> recipientCodes, WorkflowStep newStep, Workflow workflow, SetDocStatusBean input, UserDTO user, Object busObj) throws Exception{
		List<String> actRecipients = new ArrayList<String>();
		
		if(recipientCodes != null && !recipientCodes.isEmpty()) {
			List<WorkflowRecipient> recipients = new ArrayList<WorkflowRecipient>();
			
			for(String recipientCode : recipientCodes) {
				WorkflowRecipient recipient = null;
				for(WorkflowRecipient rr : workflow.getRecipients()) {
					if(rr.getWorkflowRecipientCode().equals(recipientCode)) {
						recipient = rr;
						break;
					}
				}
				if(recipient.getUserName() != null && recipient.getUserName().length() > 0) {
					if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
						actRecipients.add(recipient.getUserName());
					}
				} else if(!Strings.isNullOrEmpty(recipient.getRoleObject())) {
					Object targetObject = null;
					if(recipient.getRoleObject().equals(workflow.getBusObjCat())) {
						targetObject = busObj;
					} else if(!Strings.isNullOrEmpty(recipient.getRoleReference())) {
						targetObject = getGenericObjectByIdSync(Class.forName(recipient.getRoleObject()), BeanUtil.declaredForcedSilent.getProperty(busObj, recipient.getRoleReference()));
					}
					if(targetObject != null) {
						Object foundUsers = BeanUtil.declaredForcedSilent.getProperty(targetObject, recipient.getRoleField());
						if(foundUsers != null) {
							if(foundUsers instanceof String) {
								if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
									actRecipients.add((String)foundUsers);
								} else {
									
								}
							} else if(foundUsers instanceof List) {
								for(String ff : (List<String>) foundUsers) {
									if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
										actRecipients.add(ff);
									}
								}
								
							}
						}
					}
				} else if(!Strings.isNullOrEmpty(recipient.getRoleAPI())) {
					Object recipientAPiResults = invokeApi(recipient.getRoleAPI(), busObj, user, input);
					if(recipientAPiResults != null && recipientAPiResults instanceof List) {
						actRecipients.addAll((List<String>) recipientAPiResults);
					}
				}
			}
		}
		return actRecipients;
	}
	
	private void getRecipientsForStep(WorkflowStep newStep, Workflow workflow, SetDocStatusBean input, UserDTO user, Object busObj) throws Exception{
		if(newStep.getOwners() != null && !newStep.getOwners().isEmpty()) {
			List<WorkflowRecipient> recipients = new ArrayList<WorkflowRecipient>();
			for(WorkflowStepWorkflowRecipientXref recipientXref : newStep.getOwners()) {
				if(recipientXref.getWorkflowRecipient() != null) {
					recipients.add(recipientXref.getWorkflowRecipient());
				}
			}
			Collections.sort(recipients, new Comparator<WorkflowRecipient>() {
				@Override
				public int compare(WorkflowRecipient o1, WorkflowRecipient o2) {
					return (o1.getRank() != null ? o1.getRank() : 1) - (o2.getRank() != null ? o2.getRank() : 1);
				}
			});
			for(WorkflowRecipient recipient : recipients) {
				if(!newStep.getAllOwners() && !newStep.getUsers().isEmpty()) {
					return;
				}
				if(recipient.getUserName() != null && recipient.getUserName().length() > 0) {
					if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
						newStep.getUsers().add(recipient.getUserName());
					}
				} else if(!Strings.isNullOrEmpty(recipient.getRoleObject())) {
					Object targetObject = null;
					if(recipient.getRoleObject().equals(workflow.getBusObjCat())) {
						targetObject = busObj;
					} else if(!Strings.isNullOrEmpty(recipient.getRoleReference())) {
						targetObject = getGenericObjectByIdSync(Class.forName(recipient.getRoleObject()), BeanUtil.declaredForcedSilent.getProperty(busObj, recipient.getRoleReference()));
					}
					if(targetObject != null) {
						Object foundUsers = BeanUtil.declaredForcedSilent.getProperty(targetObject, recipient.getRoleField());
						if(foundUsers != null) {
							if(foundUsers instanceof String) {
								if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
									newStep.getUsers().add((String)foundUsers);
								} else {
									
								}
							} else if(foundUsers instanceof List) {
								for(String ff : (List<String>) foundUsers) {
									if(!newStep.getSkipPrevious() || recipient.getUserName().equals(user.getUserName())) {
										newStep.getUsers().add(ff);
									}
								}
								
							}
						}
					}
				} else if(!Strings.isNullOrEmpty(recipient.getRoleAPI())) {
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("workflowStep",  newStep);
					params.put("document", busObj);
					params.put("input", input);
					Object recipientAPiResults = apiSerService.invokeApi(recipient.getRoleAPI(), params);
					if(recipientAPiResults != null && recipientAPiResults instanceof List) {
						newStep.getUsers().addAll((List<String>) recipientAPiResults);
					}
				}
			}
		}
	}
	
	private List<WorkflowStatusDTO.State> convertStepTOState(List<WorkflowStep> steps, WorkflowStatusDTO status){
		List<WorkflowStatusDTO.State> states = new ArrayList<WorkflowStatusDTO.State>();
		for(WorkflowStep step : steps) {
			states.add(this.convertStepTOState(step, status));
		}
		return states;
	}
	
	private WorkflowStatusDTO.State convertStepTOState(WorkflowStep step, WorkflowStatusDTO status){
		WorkflowStatusDTO.State state = status.new State();
		if(step != null) {
			state.setDueOn(step.getPlannedEnd());
			state.setIcon(step.getIconId());
			state.setPreferred(false);
			state.setRecipientUsers(step.getUsers());
			state.setResponse(step.getLabel());
			state.setSequence(step.getSeq());
			state.setCommentRequired(step.getCommentRequired());
			state.setSetByUser(step.getCompletedById());
	//		state.setSetByFullName(setByFullName);
			state.setSetOn(step.getActualStart());
			state.setStatusID(step.getWorkflowStepCode());
	//		state.setStatusIDs(statusIDs);
			state.setText(step.getText());
	//		state.setTooltip(step.get);
			state.setType(step.getType().getType());
			state.setStatus(step.getBusObjCatStatus());
			if(step.getAttributes() != null) {
				//LOG.info("convertStepTOState : "+ step.getId() + "  :  "+ (step.getAttributes() == null ? " 0 ": step.getAttributes().size())+" attributes");
				state.setAttributes(step.getAttributes());
			}
		}
		return state;
	}

	@Override
	public List<WorkflowStepDTO> getWorkflowStepsForUser(UserDTO user, String busObjCat) {
		List<WorkflowStepDTO> steps = new ArrayList<>();
		try {
			CompletionStage<List<Workflow>> workflowGet = ((WorkflowRepository)this.getDaoRepository()).findByBusObjCatAndTemplateAndDisplayTemplate(busObjCat, Boolean.TRUE, Boolean.TRUE);
			List<Workflow> workflows = workflowGet.toCompletableFuture().get();
			for(Workflow workflow : workflows) {
				WorkflowDTO workflowDTO = (WorkflowDTO)getMapper().entityToDTO(workflow);
				for(WorkflowStepDTO step : workflowDTO.getSteps()) {
					//TODO add check here for user eligibility here
					boolean added = false;
					//if(user.getUserRoles()!= null && user.getUserRoles().size() > 0) {
						//for (UserRoleDTO userRole : user.getUserRoles()) {
							//if(step.getAccessRoles().contains(userRole.getRole().getRoleDescription())) {
								steps.add(step);
								added = true;
								break;
							//}
						//}
					//}
					//if(!added && step.getAccessRoles().contains(user.getUserRole())) {
						//steps.add(step);
					//}
					
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return steps;
	}
	
	@Override
	public List<MessageLogDTO> getWorkflowComments(SetDocStatusBean input) throws Exception{
		List<MessageLogDTO> comments = new ArrayList<MessageLogDTO>();
		
		if (input.getWorkflowID() == null || input.getWorkflowID().longValue() == 0) {
			return comments;
		}
		
		Workflow workflow = getByIdSync(input.getWorkflowID());
		if (workflow == null) {
			throw new RuntimeException("Workflow id passed is not valid");
		}
		//this.getDaoRepository().getEntityManager().refresh(workflow);
		if(workflow.getSteps() != null) {
			for(WorkflowStep step : workflow.getSteps()) {
				if(input.getSubID() == null || (step.getParallelTasks() != null && step.getParallelId() != null && input.getSubID().longValue() == step.getParallelId().longValue())) {
					if(!Strings.isNullOrEmpty(step.getPriorStepComments())) {
						MessageLogDTO mLog = new MessageLogDTO();
						mLog.setBody(step.getPriorStepComments());
						//mLog.setPublishedOn(step.getActualStart());
						WorkflowStep previousStep = null;
						if(!Strings.isNullOrEmpty(step.getPreviousStep())) {
							for(WorkflowStep sstep : workflow.getSteps()) {
								
								if((input.getSubID() == null || (sstep.getParallelTasks() != null && sstep.getParallelId() != null && input.getSubID().longValue() == sstep.getParallelId().longValue())) 
										&& step.getPreviousStep().equals(sstep.getWorkflowStepCode())) {
									previousStep = sstep;
									break;
								}
							}
						}
						if(previousStep != null && previousStep.getCompletedById() != null) {
							/*mLog.setPublishedByUserId(previousStep.getCompletedById());
							mLog.setPublishedOn(previousStep.getActualEnd());
							UserDTO user = userService.getById(previousStep.getCompletedById());
							if(user != null) {
								mLog.setPublishedByUserName(user.getFirstName()+" "+(user.getLastName() != null ? user.getLastName() : ""));
							}*/
						}
						comments.add(mLog);
					}
				}
			}
		}
		
		return comments;
	}
	

	@Override
	public WorkflowStatusDTO getStatusHistory(SetDocStatusBean input) throws Exception {
		WorkflowStatusDTO result = new WorkflowStatusDTO();

		//Semaphore lock = lockService.getLock(LockType.WORKFLOW.getType(), input.getWorkflowID());
		//lock.acquire();
		try {
			List<WorkflowStep> finalNextSteps = new ArrayList<WorkflowStep>();

			if (input.getWorkflowID() == null || input.getWorkflowID().longValue() == 0) {
				return result;
			}

			Workflow workflow = getByIdSync(input.getWorkflowID());
			if (workflow == null) {
				throw new RuntimeException("Workflow id passed is not valid");
			}
			Object busObj = getGenericObjectByIdSync(Class.forName(workflow.getBusObjCat()), workflow.getBusObjId());
			//workflowDao.getEntityManager().refresh(workflow);


			UserDTO user = input.getUser();

			WorkflowStep currentStep = workflow.getSteps().stream().filter(step -> step.getStartStep()).findFirst()
					.orElse(null);
			
			boolean anyChildLevelStep = false;
			Map<String, Boolean> stepsChildProcessesReached= new HashMap<String, Boolean>();
			for(WorkflowStep step : workflow.getSteps()) {
				if(step.getParallelTasks() != null && !Strings.isNullOrEmpty(step.getParallelTasks().getType())) {
					anyChildLevelStep = true;
					break;
				}
			}
			if(!anyChildLevelStep) {
				input.setSubID(null);
			}
			if(anyChildLevelStep) {
				for(WorkflowStep step : workflow.getSteps()) {
					if(step.getParallelTasks() != null && !Strings.isNullOrEmpty(step.getParallelTasks().getType()) && step.getParallelId() != null && input.getSubID().longValue() == step.getParallelId().longValue()){
						stepsChildProcessesReached.put(step.getWorkflowStepCode(), Boolean.TRUE);
					}
				}
			}

			if (currentStep != null) {
				Map<String, WorkflowStatusDTO.State> otherStatesMap = new HashMap<String, WorkflowStatusDTO.State>();
				boolean isNotCompleted = true;
				while (isNotCompleted) {
					if (!Strings.isNullOrEmpty(currentStep.getMilestone())
							&& (WorkflowStepType.APPROVAL.equals(currentStep.getType())
									|| WorkflowStepType.DATA.equals(currentStep.getType())
									|| WorkflowStepType.SYSTEM.equals(currentStep.getType()))) {
						if (otherStatesMap.get(currentStep.getMilestone()) != null) {
							otherStatesMap.get(currentStep.getMilestone()).getStatusIDs()
									.add(currentStep.getWorkflowStepCode());
							otherStatesMap.get(currentStep.getMilestone()).getWorkflowStepIDs()
							.add(currentStep.getId());
							if(input.getSubID() == null || (currentStep.getParallelId() != null && input.getSubID().longValue() == currentStep.getParallelId().longValue()))
							{
							if (otherStatesMap.get(currentStep.getMilestone()).getSetOn() == null) {
								otherStatesMap.get(currentStep.getMilestone()).setSetOn(currentStep.getActualStart());
							} else if (currentStep.getActualStart() != null && otherStatesMap
									.get(currentStep.getMilestone()).getSetOn().before(currentStep.getActualStart())) {
								otherStatesMap.get(currentStep.getMilestone()).setSetOn(currentStep.getActualStart());
							}
							}
						} else {
							WorkflowStatusDTO.State state = result.new State();
							state.setText(currentStep.getMilestone());
							state.setMilestoneDescription(currentStep.getMilestoneDescription());
							state.getStatusIDs().add(currentStep.getWorkflowStepCode());
							state.getWorkflowStepIDs().add(currentStep.getId());
							state.setSetOn(currentStep.getActualStart());
							otherStatesMap.put(currentStep.getMilestone(), state);
							result.getOtherState().add(state);
						}
					}

					List<WorkflowStep> nextSteps = new ArrayList<>();
					for (WorkflowConnection conn : workflow.getConnections()) {
						if (currentStep.getWorkflowStepCode().equalsIgnoreCase(conn.getSource())) {
							for (WorkflowStep step2 : workflow.getSteps()) {
								
									if (step2.getWorkflowStepCode().equalsIgnoreCase(conn.getTarget())) {
										
										if((input.getSubID() != null && (step2.getParallelTasks() != null && (step2.getParallelId() != null && input.getSubID().longValue() == step2.getParallelId().longValue()))
										|| (step2.getParallelId() == null && !stepsChildProcessesReached.containsKey(step2.getWorkflowStepCode()) &&  checkWorkflowConnectionCriteria(currentStep, step2, conn, workflow, busObj, input)) ) ||
												(input.getSubID() == null && checkWorkflowConnectionCriteria(currentStep, step2, conn, workflow, busObj, input) ))
										{
										if(!Strings.isNullOrEmpty(conn.getResponse())) {
												step2.setLabel(conn.getResponse());
											}
											nextSteps.add(step2);
											break;
										}
									}
							}
						}
					}

					if (nextSteps == null || nextSteps.size() == 0)
						isNotCompleted = false;
					else {
						if (nextSteps.size() == 1)
							currentStep = nextSteps.get(0);
						else if (nextSteps.size() > 1) {
							isNotCompleted = false;
							for (WorkflowStep nextStep : nextSteps) {
								if (nextStep.getActualStart() != null) {
									currentStep = nextStep;
									isNotCompleted = true;
									break;
								} else {
									for (WorkflowConnection conn : workflow.getConnections()) {
										if (currentStep.getWorkflowStepCode().equalsIgnoreCase(conn.getSource())
												&& nextStep.getWorkflowStepCode().equalsIgnoreCase(conn.getTarget())
												&& conn.getPreferred()) {
											currentStep = nextStep;
											isNotCompleted = true;
											break;
										}
									}
									
								}
							}

						}
					}

				}

				for (WorkflowStatusDTO.State state : result.getOtherState()) {
					boolean allStepsCompleted = true;
					boolean anyStepStarted = false;
					for (Long workflowStepId : state.getWorkflowStepIDs()) {
						for (WorkflowStep step : workflow.getSteps()) {
							if (workflowStepId.equals(step.getId())) {
								
									if (step.getActualStart() != null) {
										anyStepStarted = true;
									}
									if (step.getActualEnd() == null) {
										allStepsCompleted = false;
									}
									break;
							}
						}
					}
					if (allStepsCompleted) {
						state.setCompleted(Boolean.TRUE);
					} else if (anyStepStarted && !allStepsCompleted) {
						state.setCurrent(Boolean.TRUE);
					}
				}
			}

		} finally {
			//lock.release();
		}

		return result;
	}
	
	@Override
	public Date getEndDateForStep(Long workflowId, Long subId, String stepCode) throws Exception{
		Workflow workflow = getByIdSync(workflowId);
		Date stepEndDate = null;
		for(WorkflowStep step : workflow.getSteps()) {
			if(step.getWorkflowStepCode().equalsIgnoreCase(stepCode)) {
				stepEndDate = step.getActualEnd();
				break;
			}
		}
		return stepEndDate;
	}
	
	@Override
	public Date getStartDateForStep(Long workflowId, Long subId, String stepCode) throws Exception{
		Workflow workflow = getByIdSync(workflowId);
		Date stepStartDate = null;
		for(WorkflowStep step : workflow.getSteps()) {
			if(step.getWorkflowStepCode().equalsIgnoreCase(stepCode)) {
				if(subId != null && !subId.equals(step.getParallelId())) {
					continue;
				}
				if(stepStartDate == null) {
					stepStartDate = step.getActualStart();
				}else if(stepStartDate!= null && stepStartDate.after(step.getActualStart())) {
					stepStartDate = step.getActualStart();
				}
				break;
			}
		}
		return stepStartDate;
	}
	
	private Map<String, String> getFieldValues(Object busObj, WorkflowStep step){
		Map<String, String> fieldValues = new HashMap<String, String>();
		return fieldValues;
	}

	@Override
	public Map<String,String> getWorkflowStepCodeText(String busObjCat) {
		Map<String,String> stepCodeTextMap = new HashMap<>();
		try {
			CompletionStage<List<Workflow>> workflowGet = ((WorkflowRepository)this.getDaoRepository()).findByBusObjCatAndTemplate(busObjCat, Boolean.TRUE);
			List<Workflow> workflows = workflowGet.toCompletableFuture().get();
			for(Workflow workflow : workflows) {
				WorkflowDTO workflowDTO = (WorkflowDTO)getMapper().entityToDTO(workflow);
				for(WorkflowStepDTO step : workflowDTO.getSteps()) 
					stepCodeTextMap.put(step.getWorkflowStepCode(), step.getText());
					
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return stepCodeTextMap;
	}

	@Override
	public WorkflowStep getEndStep(BaseEntity busObj) {
		
		String busObjCat = busObj.getClass().getName();
		List<Workflow> workflows = null;
		try {
			if(workflows == null || workflows.isEmpty()) {
				CompletionStage<List<Workflow>> workflowGet = ((WorkflowRepository)this.getDaoRepository()).findByBusObjCatAndTemplate(busObjCat, Boolean.TRUE);
				workflows = workflowGet.toCompletableFuture().get();
			}
			
			if(workflows == null || workflows.isEmpty()) {
				return null;
			}
			Workflow workflow = null;
			for(Workflow wrk : workflows) {
				if(wrk.getIntStatus() != null && wrk.getIntStatus().intValue() == 1) {
					workflow = wrk;
					break;
				}
			}
			if(workflow == null) {
				return null;
			}
			
			List<WorkflowStep> ws = workflow.getSteps();
			
			for (WorkflowStep workflowStep : ws) {
				if(workflowStep.getEndStep()) {
					return workflowStep;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private Workflow getByIdSync(Long workflowId) {
		Workflow workflow = null;
		try {	
			CompletionStage<Optional<Workflow>> workflowGet = this.getDaoRepository().get(null, workflowId);
	
			Optional<Workflow> workflowOpt = workflowGet.toCompletableFuture().get();
	
			if (workflowOpt.isPresent()) {
				workflow = workflowOpt.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;
	
	}
	
	private Object getGenericObjectByIdSync(Class clazz, Long id) {
		Object data = null;
		try {	
			CompletionStage<Optional> objectGet = this.getDaoRepository().getGenericObjectById(null, clazz, id);
	
			Optional objectOpt = objectGet.toCompletableFuture().get();
	
			if (objectOpt.isPresent()) {
				data = objectOpt.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	
	}
	
	private MessageLog saveMessageLogSync(MessageLog messageLog) {
		try {	
			CompletionStage<Optional<MessageLog>> messageLogGet = messageLogService.saveEntity(null, messageLog);

			Optional<MessageLog> messageLogOpt = messageLogGet.toCompletableFuture().get();

			if (messageLogOpt.isPresent()) {
				messageLog = messageLogOpt.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return messageLog;	
	}
	
	private Workflow saveSync(Workflow workflow) {
		try {	
			CompletionStage<Optional<Workflow>> workflowGet = this.getDaoRepository().save(null, workflow);

			Optional<Workflow> workflowOpt = workflowGet.toCompletableFuture().get();

			if (workflowOpt.isPresent()) {
				workflow = workflowOpt.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workflow;	
	}
	
	private Object saveGenericSync(Object busObj) {
		try {	
			CompletionStage<Optional> cSave = this.getDaoRepository().saveGeneric(null, busObj);

			Optional<Object> cOpt = cSave.toCompletableFuture().get();

			if (cOpt.isPresent()) {
				busObj = cOpt.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return busObj;	
	}

}
