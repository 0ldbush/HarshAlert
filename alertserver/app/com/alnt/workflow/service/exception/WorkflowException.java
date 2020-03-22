package com.alnt.workflow.service.exception;

public class WorkflowException extends Exception { //MDBusinessException {


	private static final long serialVersionUID = 2836710408913206189L;

	public WorkflowException(String errorCode, Throwable e) {
		super(errorCode, e);
	}

	/**
	 * @param errorCode
	 */
	public WorkflowException(String errorCode) {
		super(errorCode);
	}

	/**
	 * @param errorCodeEnum
	 * @param e
	 */
	/*public WorkflowException(ErrorType errorType, Exception e) {
		super(errorType, e);
	}*/

	/**
	 * @param errorCodeEnum
	 */
	/*public WorkflowException(ErrorType errorType) {
		super(errorType);
	}*/


}
