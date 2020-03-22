package com.alnt.platform.base.exception;

public interface IException {
	
	final String SYSTEM_EXCEPTION_TYPE = "S";
	final String BUSINESS_EXCEPTION_TYPE = "B";

	/**
	 * Returns the exception type.
	 * 
	 * @return
	 */
	String getExceptionType();

	/**
	 * Sets the exception type. Currently possible values are system and
	 * business
	 * 
	 * @param exceptionType
	 */
	void setExceptionType(String exceptionType);

}
