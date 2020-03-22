package com.alnt.platform.core.messagemaster.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.alnt.platform.base.domain.Entity;

public class MessageMasterButton extends Entity{

	private static final long serialVersionUID = 1L;
	
	@Column(name="SEQ")
	private int seq;
    
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
    
    @ManyToOne(targetEntity = MessageMaster.class, optional = false, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "MESSAGE_MASTER_ID")
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
