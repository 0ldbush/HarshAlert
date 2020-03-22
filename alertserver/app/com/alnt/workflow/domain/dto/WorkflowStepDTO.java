package com.alnt.workflow.domain.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.presentation.JsonViews;
import com.alnt.workflow.service.type.WorkflowStepParallelTasksType;
import com.alnt.workflow.service.type.WorkflowStepType;
import com.fasterxml.jackson.annotation.JsonView;

public class WorkflowStepDTO extends DTO {

	private static final long serialVersionUID = -3002234805984620743L;

	@JsonView(JsonViews.Header.class)
	protected Long id;

	@JsonView(JsonViews.Full.class)
	protected Integer intStatus;

	@JsonView(JsonViews.Full.class)
	private Integer seq;

	@JsonView(JsonViews.Full.class)
	private Boolean startStep;
	
	@JsonView(JsonViews.Full.class)
	private Boolean endStep;

	@JsonView(JsonViews.Header.class)
	private String milestone;
	
	@JsonView(JsonViews.Header.class)
	private String milestoneDescription;

	@JsonView(JsonViews.Header.class)
	private String workflowStepCode;

	@JsonView(JsonViews.Header.class)
	private String label;

	@JsonView(JsonViews.Header.class)
	private String text;

	@JsonView(JsonViews.Header.class)
	private String color;

	@JsonView(JsonViews.Header.class)
	private String iconId;

	@JsonView(JsonViews.Header.class)
	private String type;

	@JsonView(JsonViews.Full.class)
	protected List<WorkflowStepWorkflowRecipientXrefDTO> owners = new ArrayList<WorkflowStepWorkflowRecipientXrefDTO>(10);

	@JsonView(JsonViews.Full.class)
	private Set<String> users = new HashSet<String>();

	@JsonView(JsonViews.Full.class)
	private Set<Long> customers = new HashSet<Long>();
	
	@JsonView(JsonViews.Full.class)
	private Set<String> accessRoles = new HashSet<String>();

	@JsonView(JsonViews.Full.class)
	protected Long workflowId;

	@JsonView(JsonViews.Full.class)
	private Boolean allOwners;

	@JsonView(JsonViews.Full.class)
	private Boolean skipPrevious;

	@JsonView(JsonViews.Full.class)
	private Long forwardedTo;

	@JsonView(JsonViews.Full.class)
	private Set<String> notifications = new HashSet<String>();

	@JsonView(JsonViews.Full.class)
	private Set<String> reminders = new HashSet<String>();

	@JsonView(JsonViews.Full.class)
	private String actionMessageCode;

	@JsonView(JsonViews.Full.class)
	private String notificationMessagesgCode;

	@JsonView(JsonViews.Full.class)
	private String reminderMessageCode;

	@JsonView(JsonViews.Full.class)
	private String formMsgId;

	@JsonView(JsonViews.Full.class)
	private String entryApi;

	@JsonView(JsonViews.Full.class)
	private String exitApi;

	@JsonView(JsonViews.Full.class)
	private Integer systemStatus = null;

	@JsonView(JsonViews.Full.class)
	private Integer leadTime;

	@JsonView(JsonViews.Full.class)
	private Date fromDate;

	@JsonView(JsonViews.Full.class)
	private Boolean returnStep;

	@JsonView(JsonViews.Full.class)
	private Boolean forward;

	@JsonView(JsonViews.Full.class)
	private Boolean commentRequired;

	@JsonView(JsonViews.Full.class)
	private String parallelTasks;

	@JsonView(JsonViews.Full.class)
	private String parallelField;

	@JsonView(JsonViews.Full.class)
	private Integer parallelCloseCount;

	@JsonView(JsonViews.Full.class)
	private Integer currentCloseCount;

	@JsonView(JsonViews.Full.class)
	private Boolean generated;

	@JsonView(JsonViews.Full.class)
	private Long parallelId;

	@JsonView(JsonViews.Full.class)
	private Date plannedStart;

	@JsonView(JsonViews.Full.class)
	private Date plannedEnd;

	@JsonView(JsonViews.Full.class)
	private Date actualStart = null;

	@JsonView(JsonViews.Full.class)
	private Date actualEnd = null;

	@JsonView(JsonViews.Full.class)
	private String previousStep;

	@JsonView(JsonViews.Full.class)
	private Long startedById;

	@JsonView(JsonViews.Full.class)
	private Long completedById;

	@JsonView(JsonViews.Full.class)
	private Map<String, String> fieldValues = new HashMap<String, String>();
	
	@JsonView(JsonViews.Header.class)
	private Map<String, String> attributes = new HashMap<String, String>();

	@JsonView(JsonViews.Full.class)
	private Integer repeat;

	@JsonView(JsonViews.Full.class)
	private Set<Long> messages = new HashSet<Long>();

	@JsonView(JsonViews.Full.class)
	private String priorStepComments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIntStatus() {
		return intStatus;
	}

	public void setIntStatus(Integer intStatus) {
		this.intStatus = intStatus;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Boolean getStartStep() {
		return startStep;
	}

	public void setStartStep(Boolean startStep) {
		this.startStep = startStep;
	}
	
	public Boolean getEndStep() {
		return endStep != null ? endStep : Boolean.FALSE;
	}

	public void setEndStep(Boolean endStep) {
		this.endStep = endStep;
	}

	public String getMilestone() {
		return milestone;
	}

	public void setMilestone(String milestone) {
		this.milestone = milestone;
	}

	public String getWorkflowStepCode() {
		return workflowStepCode;
	}

	public void setWorkflowStepCode(String workflowStepCode) {
		this.workflowStepCode = workflowStepCode;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public WorkflowStepType getType() {
		return WorkflowStepType.getInstance(type);
	}

	public void setType(WorkflowStepType type) {
		this.type = type == null ? null : type.getType();
	}

	public List<WorkflowStepWorkflowRecipientXrefDTO> getOwners() {
		return owners;
	}

	public void setOwners(List<WorkflowStepWorkflowRecipientXrefDTO> owners) {
		this.owners = owners;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

	public Set<String> getAccessRoles() {
		return accessRoles;
	}

	public void setAccessRoles(Set<String> accessRoles) {
		this.accessRoles = accessRoles;
	}

	public Set<Long> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Long> customers) {
		this.customers = customers;
	}

	public Long getWorkflowId() {
		return workflowId;
	}

	public void setWorkflowId(Long workflowId) {
		this.workflowId = workflowId;
	}

	public Boolean getAllOwners() {
		return allOwners;
	}

	public void setAllOwners(Boolean allOwners) {
		this.allOwners = allOwners;
	}

	public Boolean getSkipPrevious() {
		return skipPrevious;
	}

	public void setSkipPrevious(Boolean skipPrevious) {
		this.skipPrevious = skipPrevious;
	}

	public Long getForwardedTo() {
		return forwardedTo;
	}

	public void setForwardedTo(Long forwardedTo) {
		this.forwardedTo = forwardedTo;
	}

	public Set<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(Set<String> notifications) {
		this.notifications = notifications;
	}

	public Set<String> getReminders() {
		return reminders;
	}

	public void setReminders(Set<String> reminders) {
		this.reminders = reminders;
	}

	public String getActionMessageCode() {
		return actionMessageCode;
	}

	public void setActionMessageCode(String actionMessageCode) {
		this.actionMessageCode = actionMessageCode;
	}

	public String getNotificationMessagesgCode() {
		return notificationMessagesgCode;
	}

	public void setNotificationMessagesgCode(String notificationMessagesgCode) {
		this.notificationMessagesgCode = notificationMessagesgCode;
	}

	public String getReminderMessageCode() {
		return reminderMessageCode;
	}

	public void setReminderMessageCode(String reminderMessageCode) {
		this.reminderMessageCode = reminderMessageCode;
	}

	public String getFormMsgId() {
		return formMsgId;
	}

	public void setFormMsgId(String formMsgId) {
		this.formMsgId = formMsgId;
	}

	public String getEntryApi() {
		return entryApi;
	}

	public void setEntryApi(String entryApi) {
		this.entryApi = entryApi;
	}

	public String getExitApi() {
		return exitApi;
	}

	public void setExitApi(String exitApi) {
		this.exitApi = exitApi;
	}

	public Integer getSystemStatus() {
		return systemStatus;
	}

	public void setSystemStatus(Integer systemStatus) {
		this.systemStatus = systemStatus;
	}

	public Integer getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(Integer leadTime) {
		this.leadTime = leadTime;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Boolean getReturnStep() {
		return returnStep;
	}

	public void setReturnStep(Boolean returnStep) {
		this.returnStep = returnStep;
	}

	public Boolean getForward() {
		return forward;
	}

	public void setForward(Boolean forward) {
		this.forward = forward;
	}

	public Boolean getCommentRequired() {
		return commentRequired;
	}

	public void setCommentRequired(Boolean commentRequired) {
		this.commentRequired = commentRequired;
	}

	public WorkflowStepParallelTasksType getParallelTasks() {
		return WorkflowStepParallelTasksType.getInstance(parallelTasks);
	}

	public void setParallelTasks(WorkflowStepParallelTasksType parallelTasks) {
		this.parallelTasks = parallelTasks == null ? null : parallelTasks.getType();
	}

	public String getParallelField() {
		return parallelField;
	}

	public void setParallelField(String parallelField) {
		this.parallelField = parallelField;
	}

	public Integer getParallelCloseCount() {
		return parallelCloseCount;
	}

	public void setParallelCloseCount(Integer parallelCloseCount) {
		this.parallelCloseCount = parallelCloseCount;
	}

	public Integer getCurrentCloseCount() {
		return currentCloseCount;
	}

	public void setCurrentCloseCount(Integer currentCloseCount) {
		this.currentCloseCount = currentCloseCount;
	}

	public Boolean getGenerated() {
		return generated;
	}

	public void setGenerated(Boolean generated) {
		this.generated = generated;
	}

	public Long getParallelId() {
		return parallelId;
	}

	public void setParallelId(Long parallelId) {
		this.parallelId = parallelId;
	}

	public Date getPlannedStart() {
		return plannedStart;
	}

	public void setPlannedStart(Date plannedStart) {
		this.plannedStart = plannedStart;
	}

	public Date getPlannedEnd() {
		return plannedEnd;
	}

	public void setPlannedEnd(Date plannedEnd) {
		this.plannedEnd = plannedEnd;
	}

	public Date getActualStart() {
		return actualStart;
	}

	public void setActualStart(Date actualStart) {
		this.actualStart = actualStart;
	}

	public Date getActualEnd() {
		return actualEnd;
	}

	public void setActualEnd(Date actualEnd) {
		this.actualEnd = actualEnd;
	}

	public String getPreviousStep() {
		return previousStep;
	}

	public void setPreviousStep(String previousStep) {
		this.previousStep = previousStep;
	}

	public Long getStartedById() {
		return startedById;
	}

	public void setStartedById(Long startedById) {
		this.startedById = startedById;
	}

	public Long getCompletedById() {
		return completedById;
	}

	public void setCompletedById(Long completedById) {
		this.completedById = completedById;
	}

	public Map<String, String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(Map<String, String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public Integer getRepeat() {
		return repeat;
	}

	public void setRepeat(Integer repeat) {
		this.repeat = repeat;
	}

	public Set<Long> getMessages() {
		return messages;
	}

	public void setMessages(Set<Long> messages) {
		this.messages = messages;
	}

	public String getPriorStepComments() {
		return priorStepComments;
	}

	public void setPriorStepComments(String priorStepComments) {
		this.priorStepComments = priorStepComments;
	}
	
	public String getMilestoneDescription() {
		return milestoneDescription;
	}

	public void setMilestoneDescription(String milestoneDescription) {
		this.milestoneDescription = milestoneDescription;
	}

}
