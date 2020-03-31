package com.alnt.platform.base.exception.type;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import com.alnt.platform.base.persistence.EnumerationType;
import com.alnt.platform.base.response.ApiMessage;
import com.alnt.platform.base.response.ApiMessageType;



public class ErrorType implements Serializable, EnumerationType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7799902777242508587L;

	private static final Map<String, ErrorType> TYPES = new LinkedHashMap<String, ErrorType>();
	private static final Map<String, ErrorType> CODES = new LinkedHashMap<String, ErrorType>();

	public static final ErrorType GENERIC_SERVER_ERROR = new ErrorType( "GENERIC_SERVER_ERROR","ERR99999","Oops!!! This is unexpected!!! We are on it ..");
	public static final ErrorType LOGIN_FAILED = new ErrorType("LOGIN_FAILED","ERR00000", "Invalid username or password");
	/*
	 * Error codes 10001 - 10999 are reserved for system exceptions
	 */
	public static final ErrorType SEVERE_ERROR = new ErrorType("SEVERE_ERROR","ERR10001", "");
	public static final ErrorType SERVICE_UNAVAILABLE = new ErrorType( "SERVICE_UNAVAILABLE","ERR10002","");

	/*
	 * Error codes 11001 need to be used for business exceptions
	 */
	public static final ErrorType USER_NOT_FOUND  = new ErrorType("USER_NOT_FOUND","ERR11001", "User does not exist"); 
    public static final ErrorType INVALID_PASSWORD  = new ErrorType("INVALID_PASSWORD", "ERR11002","Invalid password");
    public static final ErrorType EXCEL_INVALID_INDEX  = new ErrorType("EXCEL_INVALID_INDEX", "ERR11003","SR No index is not in sequence");
    public static final ErrorType DOC_NUMBER_RANGE_NOT_FOUND  = new ErrorType("DOC_NUMBER_RANGE_NOT_FOUND", "ERR11004","No valid document number range found");

    public static ErrorType getInstance(final String type) {
        return TYPES.get(type);
    }
    
    public static ErrorType getInstanceByCode(final String code) {
        return CODES.get(code);
    }

    private String type;
    private String code;
    private String text;

    public ErrorType() {
        //do nothing
    }

    public ErrorType(final String type, final String code, final String text) {
        this.text = text;
        setType(type);
        setCode(code);
    }

    public String getType() {
        return type;
    }

    public String getText() {
    	//return MessageProvider.getMessageText(code, MessageType.ERROR.getType(), text);
        return text;
    }
    
    public String getCode() {
        return code;
    }
    

	private void setType(final String type) {
        this.type = type;
        if (!TYPES.containsKey(type)) {
            TYPES.put(type, this);
        }
    }
	
	private void setCode(final String code) {
        this.code = code;
        if (!CODES.containsKey(code)) {
        	CODES.put(code, this);
        }
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorType other = (ErrorType) obj;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	public static ApiMessage toApiMessage(ErrorType errorType, String cause) {
		return new ApiMessage(cause, ApiMessageType.ERROR, errorType.getCode(), errorType.getText());
	}
	
	public static ApiMessage toApiMessage(ErrorType errorType, String cause, Map<String,String> valuesMap) {
		ApiMessage apiMessage =  new ApiMessage(cause, ApiMessageType.ERROR, errorType.getCode(), errorType.getText());
		apiMessage.setValuesMap(valuesMap);
		return apiMessage;
	}
	
	public static ApiMessage toApiMessage(ErrorType errorType) {
		return new ApiMessage(errorType.getType(), ApiMessageType.ERROR, errorType.getCode(), errorType.getText());
	}

  

}
