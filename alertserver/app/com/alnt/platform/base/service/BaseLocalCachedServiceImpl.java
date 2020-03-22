package com.alnt.platform.base.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.request.RequestDetails;

import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;

public class BaseLocalCachedServiceImpl<E extends Entity, D extends DTO> 
	extends BaseServiceImpl<E, D> {

	protected AsyncCacheApi cache;
	protected String cacheKeyPrefix;
	
	public BaseLocalCachedServiceImpl(AsyncCacheApi cache, String cacheKeyPrefix, HttpExecutionContext ec, BaseRepository<E> repository, BaseMapper<D, E> mapper) {
		super(ec, repository, mapper);
		this.cache = cache;
		this.cacheKeyPrefix = cacheKeyPrefix;
	}
	
	
	
	
	public CompletionStage<D> getCached(RequestDetails requestDetails, Long id) {
		return cache.getOrElseUpdate(cacheKeyPrefix+"_get_"+id.toString(),() -> {
			return this.getDaoRepository().get(requestDetails, id).thenApplyAsync(optionalData -> {
	            return getMapper().entityToDTO(optionalData.get());
	        }, ec.current());
		});
		
	}
	
	public CompletionStage<List<D>> getByCached(RequestDetails requestDetails, String fieldName, Object value) {
		return cache.getOrElseUpdate(cacheKeyPrefix+"_getBy_"+fieldName.toString()+"_"+value.toString(),() -> {
			return this.getDaoRepository().getBy(requestDetails, fieldName, value).thenApplyAsync(optionalData -> {
	            return optionalData.stream().map(ee -> getMapper().entityToDTO(ee)).collect(Collectors.toList());
	        }, ec.current());
		});
		
	}

	@Override
	public CompletionStage<Optional<D>> update(RequestDetails requestDetails, Long id, D data) {
		if(cache != null && data.getId() != null) {
			cache.remove(cacheKeyPrefix+"_get_"+data.getId().toString());
		}
		return this.getDaoRepository().update(requestDetails, id, getMapper().dtoToEntity(data)).thenApplyAsync(optionalData -> {
			return Optional.of(getMapper().entityToDTO(optionalData.get()));
        }, ec.current());
	}
	
	@Override
	public CompletionStage<Optional<D>> save(RequestDetails requestDetails, D data) {
		if(cache != null && data.getId() != null) {
			cache.remove(cacheKeyPrefix+"_get_"+data.getId().toString());
		}
		return this.getDaoRepository().save(requestDetails,  getMapper().dtoToEntity(data)).thenApplyAsync(optionalData -> {
			return Optional.of(getMapper().entityToDTO(optionalData.get()));
        }, ec.current());
	}

}
