package com.alnt.platform.core.messagemaster.domain;

import com.alnt.platform.base.domain.BaseMasterEntity;
import com.alnt.platform.base.persistence.CacheConstants;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MESSAGE_MASTER")
@SQLDelete(sql = "UPDATE MESSAGE_MASTER SET INT_STATUS = 3 WHERE ID = ?")
public class MessageMaster extends BaseMasterEntity {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "CHANNEL")
	private String channel;

	
	@Column(name = "SUBJECT", length = 5000)
	private String subject;

	@Lob
	@Type(type = "org.hibernate.type.MaterializedClobType")
	@Column(name = "BODY", length = Integer.MAX_VALUE - 1)
	private String body;

	@Column(name = "PRIORITY")
	private String priority;

	/**
	 * //System and app-specific fields used in the message
	 */
	@ElementCollection
	@CollectionTable(name = "MESSAGE_MASTER_FIELDS", joinColumns = @JoinColumn(name = "MESSAGE_MASTER_ID"))
	@Column(name = "FIELD")
	private Set<String> fields = new HashSet<String>();

	@Column(name = "ADMIN_MESSAGE")
	private boolean adminMessage;

	/**
	 * //Buttons or user responses in the message
	 */
	@OneToMany(mappedBy = "messageMaster", targetEntity = MessageMasterButton.class, cascade = {CascadeType.ALL }, orphanRemoval = true)
	@OrderBy(value = "seq")
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_WORKFLOW)
	@BatchSize(size = 50)
	protected List<MessageMasterButton> buttons = new ArrayList<>();

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
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

	public Set<String> getFields() {
		return fields;
	}

	public void setFields(Set<String> fields) {
		this.fields = fields;
	}

	public boolean isAdminMessage() {
		return adminMessage;
	}

	public void setAdminMessage(boolean adminMessage) {
		this.adminMessage = adminMessage;
	}

	public List<MessageMasterButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<MessageMasterButton> buttons) {
		this.buttons = buttons;
	}
	
	

}
