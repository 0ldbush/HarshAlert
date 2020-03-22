package com.alnt.platform.core.binaryresource.service;

import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.core.binaryresource.domain.BinaryResource;
import com.alnt.platform.core.binaryresource.domain.dto.BinaryResourceDTO;
import com.alnt.platform.core.binaryresource.mapper.BinaryResourceMapper;
import com.alnt.platform.core.binaryresource.repository.BinaryResourceRepository;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class BinaryResourceServiceImpl extends BaseServiceImpl<BinaryResource, BinaryResourceDTO> implements BinaryResourceService {
    
	@Inject
	public BinaryResourceServiceImpl(HttpExecutionContext ec, BinaryResourceRepository repository) {
		super( ec, repository, BinaryResourceMapper.INSTANCE);
	}
	
	@Override
	public CompletionStage<BinaryResourceDTO> upload(RequestDetails requestDetails, BinaryResource data) {
		return this.getDaoRepository().save(requestDetails, data).thenApplyAsync((savedData) -> {
            return getMapper().entityToDTO(savedData.get());
        }, ec.current());
	}


	

}
