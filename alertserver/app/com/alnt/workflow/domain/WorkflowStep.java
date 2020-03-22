package com.alnt.workflow.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alnt.platform.base.persistence.CacheConstants;
import com.alnt.workflow.service.type.WorkflowStepParallelTasksType;
import com.alnt.workflow.service.type.WorkflowStepType;

/**
 * Entity implementation class for Entity: WorkflowStep
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="WORKFLOW_STEP")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_WORKFLOW)
public class WorkflowStep extends com.alnt.platform.base.domain.Entity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "INT_STATUS")
    protected Integer intStatus;
	
    @Column(name="SEQ")//Approximate sequence of step in flow
	private Integer seq;

    @Column(name="IS_START_STEP")//Indicates that this is the first step of this workflow
    private Boolean startStep;
    
    @Column(name="IS_END_STEP")//Indicates that this is the last step of this workflow
    private Boolean endStep;
    
    @Column(name="MILESTONE")//Milestone or summarized status label often used to standardize reporting milestones between different workflows
	private String milestone;
    
    @Column(name="MILESTONE_DESCRIPTION")//Milestone or summarized status label often used to standardize reporting milestones between different workflows
	private String milestoneDescription;

	@Column(name="WORKFLOW_STEP_CODE", length=20)
	private String workflowStepCode;

	@Column(name="LABEL")//Name or short title of step e.g. "Submitted" or "A/W Approval"
	private String label;

	@Column(name="TEXT")//Detailed description of the action required by step owner to complete task (tooltip)
	private String text;

    @Column(name="COLOR")//Color to assign to object when in this step (optional)
	private String color;
  
    @Column(name="ICON_ID")//Icon used to represent this step (optional)
	private String iconId;

	@Column(name="TYPE")//Step/task type: Notification, Approval, Data Capture, Connection or System Step
	private String type;
  	
	@OneToMany(targetEntity = WorkflowStepWorkflowRecipientXref.class, mappedBy = "workflowStep", orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @OrderBy(value="seq")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_WORKFLOW)
    @BatchSize(size = 50)
    protected List<WorkflowStepWorkflowRecipientXref> owners = new ArrayList<WorkflowStepWorkflowRecipientXref>(10);
    
	@ElementCollection
    @CollectionTable(name = "WORKFLOW_STEP_USERS", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "USER_NAME")
    private Set<String> users = new HashSet<String>();
	
	@ElementCollection
    @CollectionTable(name = "WORKFLOW_STEP_ACCESS_ROLES", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "ROLE")
    private Set<String> accessRoles = new HashSet<String>();
	
	@ManyToOne(targetEntity = Workflow.class, optional = false)
    @JoinColumn(name = "WORKFLOW_ID")
    protected Workflow workflow;

    @Column(name="ALL_OWNERS")
	private Boolean allOwners;
    	
    @Column(name="SKIP_PREVIOUS")
	private Boolean skipPrevious;
    
    
    @Column(name="FORWARDED_TO")
	private Long forwardedTo;
    
    @ElementCollection
    @CollectionTable(name = "WORKFLOW_STEP_NOTIFICATION_RECIPIENTS", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "NOTIFICATION_RECIPIENT")
    private Set<String> notifications = new HashSet<String>();
    
    @ElementCollection
    @CollectionTable(name = "WORKFLOW_STEP_REMINDER_RECIPIENTS", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "REMINDER_RECIPIENT")
    private Set<String> reminders = new HashSet<String>();
    
    @Column(name="ACTION_MESSAGE_CODE")
	private String actionMessageCode;
	
    @Column(name="NOTIFICATION_MESSAGE_CODE")
	private String notificationMessagesgCode;
    
    @Column(name="REMINDER_MESSAGE_CODE")
	private String reminderMessageCode;
	
    @Column(name="FORM_MESSAGE_ID")
	private String formMsgId;   

    @Column(name="ENTRY_API")
	private String entryApi;     

    @Column(name="EXIT_API")
	private String exitApi;    
    
    @Column(name="SYSTEM_STATUS")
	private Integer systemStatus = null;
    
    @Column(name="BUS_OBJ_CAT_STATUS")
	private String busObjCatStatus = null;

    @Column(name="LEAD_TIME")
	private Integer leadTime;
    
	@Column(name="FROM_DATE")
	private Date fromDate;
	
    @Column(name="RETURN_STEP")
	private Boolean returnStep;

    @Column(name="FORWARD")
	private Boolean forward;
    
    @Column(name="COMMENT_REQUIRED")
	private Boolean commentRequired;
	
    @Column(name="PARALLEL_TASKS")
    private String  parallelTasks;
	
    @Column(name="PARALLEL_FIELD")
    private String  parallelField;
	
    @Column(name="PARALLEL_CLOSE_COUNT")
    private Integer  parallelCloseCount;
	
    @Column(name="CURRENT_CLOSE_COUNT")
    private Integer  currentCloseCount;
	
    @Column(name="IS_GENERATED")
	private Boolean generated;
	
    @Column(name="PARALLEL_ID")
	private Long parallelId;

    @Column(name="PLANNED_START")
	private Date plannedStart;
    
    @Column(name="PLANNED_END")
	private Date plannedEnd;
    
    @Column(name="ACTUAL_START")
	private Date actualStart = null;
    
    @Column(name="ACTUAL_END")
	private Date actualEnd = null;

    @Column(name="PREVIOUS_STEP")
	private String previousStep;    
    
    @Column(name="STARTED_BY")
	private Long startedById;
    
    @Column(name="COMPLETED_BY")
	private Long completedById;

    @ElementCollection
    @MapKeyColumn(name="FIELD_NAME")
    @CollectionTable(name = "WORKFLOW_STEP_FIELD_VALUES", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "FIELD_VALUE")
    private Map<String, String> fieldValues = new HashMap<String, String>();
    
    @ElementCollection
    @MapKeyColumn(name="NAME")
    @CollectionTable(name = "WORKFLOW_STEP_ATTRIBUTES", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "VALUE")
    private Map<String, String> attributes = new HashMap<String, String>();
    
    @Column(name="NUM_REPEAT")
	private Integer repeat;
    
    @ElementCollection
    @CollectionTable(name = "WORKFLOW_STEP_MESSAGES", joinColumns = @JoinColumn(name = "WORKFLOW_STEP_ID"))
    @Column(name = "MESSAGE_ID")
    private Set<Long> messages = new HashSet<Long>();
	
    @Column(name="PRIOR_STEP_COMMENTS")
	private String priorStepComments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Boolean getStartStep() {
		return startStep != null ? startStep : Boolean.FALSE;
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

	public List<WorkflowStepWorkflowRecipientXref> getOwners() {
		return owners;
	}

	public void setOwners(List<WorkflowStepWorkflowRecipientXref> owners) {
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

	public void setAccessRoles(Set<String> roles) {
		this.accessRoles = roles;
	}

	public Workflow getWorkflow() {
		return workflow;
	}

	public Long getWorkflowId() {
		return workflow != null ? workflow.getId() : null;
	}

	public void setWorkflow(Workflow workflow) {
		this.workflow = workflow;
	}

	public Boolean getAllOwners() {
		return allOwners != null ? allOwners : Boolean.FALSE;
	}

	public void setAllOwners(Boolean allOwners) {
		this.allOwners = allOwners;
	}

	public Boolean getSkipPrevious() {
		return skipPrevious != null ? skipPrevious : Boolean.FALSE;
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
		return returnStep != null ? returnStep : Boolean.FALSE;
	}

	public void setReturnStep(Boolean returnStep) {
		this.returnStep = returnStep;
	}

	public Boolean getForward() {
		return forward != null ? forward : Boolean.FALSE;
	}

	public void setForward(Boolean forward) {
		this.forward = forward;
	}

	public Boolean getCommentRequired() {
		return commentRequired != null ? commentRequired : Boolean.FALSE;
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
		return generated != null ? generated : Boolean.FALSE;
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

	public String getBusObjCatStatus() {
		return busObjCatStatus;
	}

	public void setBusObjCatStatus(String busObjCatStatus) {
		this.busObjCatStatus = busObjCatStatus;
	}

	public String getMilestoneDescription() {
		return milestoneDescription;
	}

	public void setMilestoneDescription(String milestoneDescription) {
		this.milestoneDescription = milestoneDescription;
	}
    
    
}
