package com.alnt.platform.base.exception;

import java.util.Map;

import com.alnt.platform.base.exception.type.ErrorType;

public class BaseBusinessException extends BaseException {

	private static final long serialVersionUID = -8827193841771676450L;


	public BaseBusinessException(String message, ErrorType errorType, Throwable cause) {
		super(message, errorType, cause);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

	public BaseBusinessException(ErrorType errorType, Throwable cause) {
		super(errorType, cause);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(String message, ErrorType errorType) {
		super(message, errorType);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

	public BaseBusinessException(String message, Throwable cause) {
		super(message, cause);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(ErrorType errorType) {
		super(errorType);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(ErrorType errorType, Map<String,String> valuesMap) {
		super(errorType, valuesMap);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(ErrorType errorType, Throwable cause, Map<String,String> valuesMap) {
		super(errorType, cause, valuesMap);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(Throwable cause) {
		super(cause);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}
	
	public BaseBusinessException(String message) {
		super(message);
		super.setExceptionType(BUSINESS_EXCEPTION_TYPE);
	}

}
