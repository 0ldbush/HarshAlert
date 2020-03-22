package com.alnt.mdm.ruleattr.service;

import com.alnt.mdm.ruleattr.domain.RuleAttr;
import com.alnt.mdm.ruleattr.domain.dto.RuleAttrDTO;
import com.alnt.platform.base.service.BaseService;
import com.google.inject.ImplementedBy;

@ImplementedBy(RuleAttrServiceImpl.class)
public interface RuleAttrService extends BaseService<RuleAttr, RuleAttrDTO> {

}
