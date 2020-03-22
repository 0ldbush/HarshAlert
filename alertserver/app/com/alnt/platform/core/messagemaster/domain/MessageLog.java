package com.alnt.platform.core.messagemaster.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseSocialEntity;


// Message or comment

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MESSAGE_LOG")
@SQLDelete(sql = "UPDATE MESSAGE_LOG SET INT_STATUS = 3 WHERE ID = ?")

public class MessageLog extends BaseSocialEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="MESSAGE_MASTER_ID")
	private String msgMasterId;
	
	@Column(name="SUBJECT")
	private String subject;

	//Message email body or long text	
	@Lob
	@Type(type = "org.hibernate.type.TextType")
	@Column(name="BODY")
	private String body;

	@Column(name="PRIORITY")
	private String priority;

	@Column(name="FORM")
	private Boolean form;

//	@ElementCollection
//    @MapKeyColumn(name="FIELD_NAME")
//    @CollectionTable(name = "MESSAGE_LOG_FIELD_VALUES", joinColumns = @JoinColumn(name = "MESSAGE_LOG_ID"))
//    @Column(name = "FIELD_VALUE")
//    private Map<String, String> fieldValues = new HashMap<String, String>();

//	@OneToMany(targetEntity = MessageButton.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
//	@JoinColumn(name="MESSAGE_LOG_ID", referencedColumnName="ID", foreignKey = @ForeignKey)
//    @OrderBy(value="seq")
//	private List<MessageButton> buttons = new ArrayList<MessageButton>();

	@Column(name="ADMIN_MESSAGE")
	private Boolean adminMsg;

	@Column(name="CONVERSATION_ID")
	private String conversationId;

	@OneToMany(targetEntity = MessageRecipient.class, cascade = {CascadeType.ALL }, orphanRemoval = true, fetch=FetchType.EAGER)
	@JoinColumn(name="MESSAGE_LOG_ID", referencedColumnName="ID", foreignKey = @ForeignKey)
    @OrderBy(value="seq")
    private List<MessageRecipient> recipients = new ArrayList<MessageRecipient>();

	public String getMsgMasterId() {
		return msgMasterId;
	}

	public void setMsgMasterID(String msgMasterId) {
		this.msgMasterId = msgMasterId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Boolean getForm() {
		return form;
	}

	public void setForm(Boolean form) {
		this.form = form;
	}

	public Map<String, String> getFieldValues() {
		return null;//fieldValues;
	}

	public void setFieldValues(Map<String, String> fieldValues) {
//		this.fieldValues = fieldValues;
	}

	public List<MessageButton> getButtons() {
		return null;//buttons;
	}

	public void setButtons(List<MessageButton> buttons) {
		//this.buttons = buttons;
	}

	public Boolean getAdminMsg() {
		return adminMsg;
	}

	public void setAdminMsg(Boolean adminMsg) {
		this.adminMsg = adminMsg;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationID(String conversationId) {
		this.conversationId = conversationId;
	}

	public List<MessageRecipient> getRecipients() {
		return recipients;
	}

	public void setRecipients(List<MessageRecipient> recipients) {
		this.recipients = recipients;
	}

	
}