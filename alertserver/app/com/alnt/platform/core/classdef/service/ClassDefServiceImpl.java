package com.alnt.platform.core.classdef.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.mapper.ClassDefMapper;
import com.alnt.platform.core.classdef.repository.ClassDefRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ClassDefServiceImpl extends BaseServiceImpl<ClassDef, ClassDefDTO> implements ClassDefService {
    
	@Inject
	public ClassDefServiceImpl(HttpExecutionContext ec, ClassDefRepository repository) {
		super( ec, repository, ClassDefMapper.INSTANCE);
	}

}
