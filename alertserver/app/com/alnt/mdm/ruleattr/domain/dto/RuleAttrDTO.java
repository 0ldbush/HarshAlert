package com.alnt.mdm.ruleattr.domain.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import com.alnt.platform.base.domain.dto.BaseDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter 
public class RuleAttrDTO extends BaseDTO {

	private static final long serialVersionUID = 1L;
	
	private String extId;

	private String text;

	private String description;
	
	private int priority;
    
	private String entity;
	
	private String dataType;
    
}

