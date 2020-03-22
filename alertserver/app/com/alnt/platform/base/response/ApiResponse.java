package com.alnt.platform.base.response;

import java.io.Serializable;
import java.util.List;

import com.alnt.platform.base.presentation.JsonViews;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView(JsonViews.Combo.class)
public class ApiResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5571836897372297592L;
	private boolean success;
	private List<ApiMessage> messages;
	@JsonView(JsonViews.Combo.class)
	private Object data;
	private int numberOfElements;
	private int totalPages;
	private long totalElements;
	private int pageNumber;
	private int pageSize;
	private String ackId;
	private Integer status;
	private String message;
	
	public ApiResponse(boolean success, Object data, List<ApiMessage> messages) {
		super();
		this.success = success;
		this.messages = messages;
		this.data = data;
	}
	public ApiResponse() {
		super();
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public List<ApiMessage> getMessages() {
		return messages;
	}
	public void setMessages(List<ApiMessage> messages) {
		this.messages = messages;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getNumberOfElements() {
		return numberOfElements;
	}
	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public long getTotalElements() {
		return totalElements;
	}
	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getAckId() {
		return ackId;
	}
	public void setAckId(String ackId) {
		this.ackId = ackId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}	
	
	
	 
}
