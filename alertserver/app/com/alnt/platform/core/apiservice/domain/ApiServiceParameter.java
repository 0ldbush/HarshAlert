package com.alnt.platform.core.apiservice.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alnt.platform.base.persistence.CacheConstants;

/**
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name="API_SERVICE_PARAMETER", indexes = {
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region=CacheConstants.REGION_API_SERVICE)
public class ApiServiceParameter extends com.alnt.platform.base.domain.Entity implements Serializable{

    private static final long serialVersionUID = 1L;
    //private static final Log LOG = LogFactory.getLog(ApiServiceParameter.class);


    @Column(name = "FIELD", nullable=false)
    protected String field;

    @Column(name = "FIELD_TYPE", nullable=false)
    protected String fieldType;

    @Column(name = "MULTIPLE_VALUES")
    protected Boolean multipleValues;

    @Column(name = "CONSTANT_VALUE")
    protected String constantValue;
    
    @Column(name = "SEQ", nullable=false)
    protected Integer sequence;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, targetEntity = ApiService.class, optional=false)
    @JoinColumn(name = "API_SERVICE_ID")
    protected ApiService apiService;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public Boolean getMultipleValues() {
		return multipleValues;
	}

	public void setMultipleValues(Boolean multipleValues) {
		this.multipleValues = multipleValues;
	}

	public String getConstantValue() {
		return constantValue;
	}

	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public ApiService getApiService() {
		return apiService;
	}
	
	public Long getApiServiceId() {
		return apiService ==  null ? null : apiService.getId();
	}

	public void setApiService(ApiService apiService) {
		this.apiService = apiService;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiService == null) ? 0 : apiService.hashCode());
		result = prime * result + ((constantValue == null) ? 0 : constantValue.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result + ((fieldType == null) ? 0 : fieldType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((multipleValues == null) ? 0 : multipleValues.hashCode());
		result = prime * result + ((sequence == null) ? 0 : sequence.hashCode());
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
		ApiServiceParameter other = (ApiServiceParameter) obj;
		if (apiService == null) {
			if (other.apiService != null)
				return false;
		} else if (!apiService.equals(other.apiService))
			return false;
		if (constantValue == null) {
			if (other.constantValue != null)
				return false;
		} else if (!constantValue.equals(other.constantValue))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (fieldType == null) {
			if (other.fieldType != null)
				return false;
		} else if (!fieldType.equals(other.fieldType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (multipleValues == null) {
			if (other.multipleValues != null)
				return false;
		} else if (!multipleValues.equals(other.multipleValues))
			return false;
		if (sequence == null) {
			if (other.sequence != null)
				return false;
		} else if (!sequence.equals(other.sequence))
			return false;
		return true;
	}
    
    

}
