package com.alnt.platform.core.apiservice.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;

import com.alnt.platform.base.domain.BaseEntity;
import com.alnt.platform.base.persistence.CacheConstants;
import com.alnt.platform.core.apiservice.service.type.ApiServiceType;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="API_SERVICE", indexes = {
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_API_SERVICE)
@SQLDelete(sql="UPDATE API_SERVICE SET INTSTATUS = 3 WHERE CATEGORY_ID = ?")
public class ApiService extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;
    //private static final Log LOG = LogFactory.getLog(ApiService.class);


    @Column(name = "NAME", nullable=false)
    protected String name;

    @Column(name = "API_SERVICE_CODE", nullable=false)
    protected String apiServiceCode;

    @Column(name = "TYPE", nullable=false)
    protected String type;

    @Column(name = "CLAZZ")
    protected String clazz;
    
    @Column(name = "METHOD")
    protected String method;
    
    @Column(name = "WEB_SERVICE")
    protected String webService;
    
    @Column(name = "FROM_QUEUE_NAME")
    protected String fromQueueName;
    
    @Column(name = "TO_QUEUE_NAME")
    protected String toQueueName;
    
    @Lob
    @Type(type = "org.hibernate.type.MaterializedClobType")
    @Column(name = "MATCH_RULE", length = Integer.MAX_VALUE - 1)
    protected String matchRule;
    
    @OneToMany(mappedBy = "apiService", targetEntity = ApiServiceParameter.class, cascade = { CascadeType.ALL }, orphanRemoval=true)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = CacheConstants.REGION_API_SERVICE)
    protected List<ApiServiceParameter> apiServiceParameters = new ArrayList<>();
	
    @Column(name="ERROR_MESSAGE_CODE")
	private String errorMessagesgCode;
    
    @Column(name="SUCCESS_MESSAGE_CODE")
	private String successMessageCode;
    
    @Column(name="GROUP_NAME")
	private String groupName;
    
    @Column(name="SEQ")
	private Integer seq;
    

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
		return ApiServiceType.getInstance(type);
	}

	public void setType(ApiServiceType type) {
		this.type = type != null ? type.getType() : null;
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

	public List<ApiServiceParameter> getApiServiceParameters() {
		return apiServiceParameters;
	}

	public void setApiServiceParameters(List<ApiServiceParameter> apiServiceParameters) {
		this.apiServiceParameters = apiServiceParameters;
	}

	public String getMatchRule() {
		return matchRule;
	}

	public void setMatchRule(String matchRule) {
		this.matchRule = matchRule;
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
		ApiService other = (ApiService) obj;
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
    
    

    
}
