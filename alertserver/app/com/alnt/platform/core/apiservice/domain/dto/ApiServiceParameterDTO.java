package com.alnt.platform.core.apiservice.domain.dto;

import com.alnt.platform.base.domain.dto.DTO;

public class ApiServiceParameterDTO extends DTO{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String field;

    protected String fieldType;

    protected Boolean multipleValues;

    protected String constantValue;
    
    protected Integer sequence;
    
    protected Long apiServiceId;


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


	public Long getApiServiceId() {
		return apiServiceId;
	}

	public void setApiServiceId(Long apiServiceId) {
		this.apiServiceId = apiServiceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apiServiceId == null) ? 0 : apiServiceId.hashCode());
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
		ApiServiceParameterDTO other = (ApiServiceParameterDTO) obj;
		if (apiServiceId == null) {
			if (other.apiServiceId != null)
				return false;
		} else if (!apiServiceId.equals(other.apiServiceId))
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
