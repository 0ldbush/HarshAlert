package com.alnt.platform.base.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiResponse;

import play.cache.AsyncCacheApi;


public interface BaseService <E extends Entity, D extends DTO> {
	
    public BaseRepository<E> getDaoRepository();
	
	public BaseMapper<D,E> getMapper();
	
	CompletionStage<Stream<D>> find(RequestDetails requestDetails, SearchCriteria searchCriteria);
	
	CompletionStage<ApiResponse> findAll(RequestDetails requestDetails, SearchCriteria searchCriteria);

//    CompletionStage<D> create(RequestDetails requestDetails, D data);

    CompletionStage<Optional<D>> get(RequestDetails requestDetails, Long id);
    
    CompletionStage<Stream<D>> getBy(RequestDetails requestDetails,String fieldName, Object value);

//    CompletionStage<Optional<D>> update(RequestDetails requestDetails, Long id, D postData);
    
    CompletionStage<Optional<D>> save(RequestDetails requestDetails, D data);
    
    CompletionStage<Optional<E>> saveEntity(RequestDetails requestDetails, E entity);

	CompletionStage<List<Optional<D>>> saveAll(RequestDetails requestDetails, List<D> dataList);

	CompletionStage<String> delete(RequestDetails requestDetails, Long id);
	void afterSave(List<Optional<E>> optionals);
	
	AsyncCacheApi getCache();
}
