package com.alnt.platform.core.messagemaster.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.core.messagemaster.domain.MessageMaster;

public class MessageMasterButtonDTO extends DTO{

	private static final long serialVersionUID = 1L;
		
	private int seq;
	
	private String clickedText;
	
	private Boolean toggle;
    
	private Boolean repeat;
	
	private String icon;
    
	private String buttonHandlerAPI;
    
	protected MessageMaster messageMaster;

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
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

	public MessageMaster getMessageMaster() {
		return messageMaster;
	}

	public void setMessageMaster(MessageMaster messageMaster) {
		this.messageMaster = messageMaster;
	}

	
}
