package com.alnt.platform.core.classdef.service;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.service.BaseLocalCachedServiceImpl;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.classdef.domain.ClassDef;
import com.alnt.platform.core.classdef.domain.dto.ClassDefDTO;
import com.alnt.platform.core.classdef.mapper.ClassDefMapper;
import com.alnt.platform.core.classdef.repository.ClassDefRepository;

import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ClassDefLocalCachedServiceImpl extends BaseLocalCachedServiceImpl<ClassDef, ClassDefDTO> implements ClassDefService {
    
	@Inject
	public ClassDefLocalCachedServiceImpl(AsyncCacheApi caceApi, HttpExecutionContext ec, ClassDefRepository repository) {
		super(caceApi, "ClassDef", ec, repository, ClassDefMapper.INSTANCE);
	}

}
