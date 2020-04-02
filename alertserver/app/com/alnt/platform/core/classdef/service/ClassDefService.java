package com.alnt.platform.core.classdef.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ClassDefServiceImpl.class)
public interface ClassDefService extends BaseService<ClassDef, ClassDefDTO> {

}
