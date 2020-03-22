package com.alnt.platform.core.messagemaster.domain.dto;


import java.util.HashMap;
import java.util.Map;

import com.alnt.platform.base.domain.dto.DTO;


// 


public class MessageButtonDTO extends DTO{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int seq;

	private String clickableText;
    
    private String clickedText;
    
    private Boolean toggle;
    
    private Boolean repeat;
	
    private String icon;
    
    private String buttonHandlerAPI;
    
    private Map<String, String> fieldValues = new HashMap<String, String>();
    
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