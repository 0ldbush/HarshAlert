package com.alnt.platform.base.exception;

import java.util.Map;

import com.alnt.platform.base.exception.type.ErrorType;

public class BaseException extends RuntimeException implements IException {

	private static final long serialVersionUID = -2832055166473397252L;
	
	private String exceptionType;
	private String message;
	private ErrorType errorType;
	private Map<String,String> valuesMap;


	BaseException(String message, ErrorType errorType, Throwable cause) {
		super(message, cause);
		this.setMessage(message);
		this.errorType = errorType;
	}

	BaseException(String message, ErrorType errorType) {
		super(message);
		this.setMessage(message);
		this.errorType = errorType;
	}
	
	BaseException(ErrorType errorType,Throwable cause) {
		super(errorType.getText(), cause);
		this.setMessage(errorType.getText());
		this.errorType = errorType;
	}
	
	BaseException(ErrorType errorType, Throwable cause, Map<String,String> valuesMap) {
		super(errorType.getText(), cause);
		this.valuesMap = valuesMap;
		this.setMessage(errorType.getText());
		this.errorType = errorType;
	}

	BaseException(ErrorType errorType) {
		super(errorType.getText());
		this.setMessage(errorType.getText());
		this.errorType = errorType;
	}
	
	BaseException(ErrorType errorType, Map<String,String> valuesMap) {
		super(errorType.getText());
		this.valuesMap = valuesMap;
		this.setMessage(errorType.getText());
		this.errorType = errorType;
	}

	public BaseException(String message) {
		super(message);
	}
	public BaseException() {
		super();
	}

	public BaseException(Throwable cause) {
		super(cause);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}


	public String getExceptionType() {
		return exceptionType;
	}


	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	public ErrorType getErrorType() {
		return errorType;
	}

	public void setErrorType(ErrorType errorType) {
		this.errorType = errorType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String,String> getValuesMap() {
		return valuesMap;
	}

	public void setValuesMap(Map<String,String> valuesMap) {
		this.valuesMap = valuesMap;
	}
}
