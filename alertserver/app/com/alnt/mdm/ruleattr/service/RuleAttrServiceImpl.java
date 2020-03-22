package com.alnt.mdm.ruleattr.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.mdm.ruleattr.domain.RuleAttr;
import com.alnt.mdm.ruleattr.domain.dto.RuleAttrDTO;
import com.alnt.mdm.ruleattr.mapper.RuleAttrMapper;
import com.alnt.mdm.ruleattr.repository.RuleAttrRepository;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class RuleAttrServiceImpl extends BaseServiceImpl<RuleAttr, RuleAttrDTO> implements RuleAttrService {
    
	@Inject
	public RuleAttrServiceImpl(HttpExecutionContext ec, RuleAttrRepository repository) {
		super( ec, repository, RuleAttrMapper.INSTANCE);
	}

	

}
