package com.alnt.platform.core.apiservice.domain.dto;

import java.util.ArrayList;
import java.util.List;

import com.alnt.platform.base.domain.dto.BaseDTO;
import com.alnt.platform.core.apiservice.service.type.ApiServiceType;

public class ApiServiceDTO extends BaseDTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    protected String name;

    protected String apiServiceCode;

    protected ApiServiceType type;

    protected String clazz;
    
    protected String method;
    
    protected String webService;
    
    protected String fromQueueName;
    
    protected String toQueueName;
    
    protected String matchRule;
    
    protected List<ApiServiceParameterDTO> apiServiceParameters = new ArrayList<>();
    
    private String errorMessagesgCode;
    
    private String successMessageCode;
    
    private String groupName;
    
    private Integer seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiServiceCode() {
		return apiServiceCode;
	}

	public void setApiServiceCode(String apiServiceCode) {
		this.apiServiceCode = apiServiceCode;
	}

	public ApiServiceType getType() {
		return type;
	}

	public void setType(ApiServiceType type) {
		this.type = type;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getWebService() {
		return webService;
	}

	public void setWebService(String webService) {
		this.webService = webService;
	}

	public List<ApiServiceParameterDTO> getApiServiceParameters() {
		return apiServiceParameters;
	}

	public void setApiServiceParameters(List<ApiServiceParameterDTO> apiServiceParameters) {
		this.apiServiceParameters = apiServiceParameters;
	}

	public String getFromQueueName() {
		return fromQueueName;
	}

	public void setFromQueueName(String fromQueueName) {
		this.fromQueueName = fromQueueName;
	}

	public String getToQueueName() {
		return toQueueName;
	}

	public void setToQueueName(String toQueueName) {
		this.toQueueName = toQueueName;
	}

	public String getMatchRule() {
		return matchRule;
	}

	public void setMatchRule(String matchRule) {
		this.matchRule = matchRule;
	}

	public String getErrorMessagesgCode() {
		return errorMessagesgCode;
	}

	public void setErrorMessagesgCode(String errorMessagesgCode) {
		this.errorMessagesgCode = errorMessagesgCode;
	}

	public String getSuccessMessageCode() {
		return successMessageCode;
	}

	public void setSuccessMessageCode(String successMessageCode) {
		this.successMessageCode = successMessageCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiServiceCode == null) ? 0 : apiServiceCode.hashCode());
		result = prime * result + ((apiServiceParameters == null) ? 0 : apiServiceParameters.hashCode());
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((webService == null) ? 0 : webService.hashCode());
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
		ApiServiceDTO other = (ApiServiceDTO) obj;
		if (apiServiceCode == null) {
			if (other.apiServiceCode != null)
				return false;
		} else if (!apiServiceCode.equals(other.apiServiceCode))
			return false;
		if (apiServiceParameters == null) {
			if (other.apiServiceParameters != null)
				return false;
		} else if (!apiServiceParameters.equals(other.apiServiceParameters))
			return false;
		if (clazz == null) {
			if (other.clazz != null)
				return false;
		} else if (!clazz.equals(other.clazz))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (webService == null) {
			if (other.webService != null)
				return false;
		} else if (!webService.equals(other.webService))
			return false;
		return true;
	}
    
    

    
}
