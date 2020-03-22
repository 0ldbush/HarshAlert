package com.alnt.platform.base.exception;

import com.alnt.platform.base.exception.BaseException;
import com.alnt.platform.base.exception.type.ErrorType;

public class BaseSystemException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8827193841771676450L;

	/**
	 * @param errorCode
	 * @param e
	 */
	public BaseSystemException(String errorCode, Exception e) {
		super(errorCode, e);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCode
	 */
	public BaseSystemException(String errorCode) {
		super(errorCode);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	public BaseSystemException(ErrorType errorType, Exception e) {
		super(errorType, e);
		super.setExceptionType(SYSTEM_EXCEPTION_TYPE);
	}

	/**
	 * @param errorCodeEnum
	 */
	public BaseSystemException(ErrorType errorType) {
		super(errorType);
		// TODO Auto-generated constructor stub
	}

}
