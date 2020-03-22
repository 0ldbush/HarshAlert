package com.alnt.platform.core.messagemaster.domain;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;


// 


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "MESSAGE_BUTTON")
public class MessageButton extends com.alnt.platform.base.domain.Entity{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="SEQ")
	private int seq;

	@Column(name="CLICKABLE_TEXT")
	private String clickableText;
    
    @Column(name="CLICKED_TEXT")
	private String clickedText;
    
    @Column(name="TOGGLE")
	private Boolean toggle;
    
    @Column(name="IS_REPEAT")
	private Boolean repeat;
	
    @Column(name="ICON")
	private String icon;
    
    @Column(name="BUTTON_HANDLER_API")
	private String buttonHandlerAPI;
    
    @ElementCollection
    @MapKeyColumn(name="FIELD_NAME")
    @CollectionTable(name = "MESSAGE_BUTTON_FIELD_VALUES", joinColumns = @JoinColumn(name = "MESSAGE_LOG_BUTTON_ID"))
    @Column(name = "FIELD_VALUE")
    private Map<String, String> fieldValues = new HashMap<String, String>();
    
    @Column(name = "MESSAGE_LOG_ID")
	protected Long messageLogId;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getClickableText() {
		return clickableText;
	}

	public void setClickableText(String clickableText) {
		this.clickableText = clickableText;
	}

	public String getClickedText() {
		return clickedText;
	}

	public void setClickedText(String clickedText) {
		this.clickedText = clickedText;
	}

	public Boolean getToggle() {
		return toggle;
	}

	public void setToggle(Boolean toggle) {
		this.toggle = toggle;
	}

	public Boolean getRepeat() {
		return repeat;
	}

	public void setRepeat(Boolean repeat) {
		this.repeat = repeat;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getButtonHandlerAPI() {
		return buttonHandlerAPI;
	}

	public void setButtonHandlerAPI(String buttonHandlerAPI) {
		this.buttonHandlerAPI = buttonHandlerAPI;
	}

	public Map<String, String> getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(Map<String, String> fieldValues) {
		this.fieldValues = fieldValues;
	}

	public Long getMessageLogId() {
		return messageLogId;
	}

	public void setMessageLogId(Long messageLogId) {
		this.messageLogId = messageLogId;
	}
    
    
}