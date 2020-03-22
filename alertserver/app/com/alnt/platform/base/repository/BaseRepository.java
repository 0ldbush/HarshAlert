package com.alnt.platform.base.repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;

import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;

public interface BaseRepository<E extends Entity> {
	
	CompletionStage<Stream<E>> list(RequestDetails requestDetails );

    CompletionStage<E> create(RequestDetails requestDetails,  E data);

    CompletionStage<Optional<E>> get(RequestDetails requestDetails,  Long id);

    CompletionStage<List<E>> getBy(RequestDetails requestDetails,  String fieldName, Object value);

    CompletionStage<Optional<E>> update(RequestDetails requestDetails,  Long id, E postData);
    
    CompletionStage<Optional<E>> save(RequestDetails requestDetails, E postData);
    
    CompletionStage<Page<E>> findAll(RequestDetails requestDetails, SearchCriteria searchParams);
    
    CompletionStage<Optional> getGenericObjectById(RequestDetails requestDetails, Class clazz, Long id);
    
    CompletionStage<Optional> saveGeneric(RequestDetails requestDetails, Object postData);
    CompletionStage<List<Optional<E>>> saveAll(RequestDetails requestDetails, List<E> postData);

    CompletionStage<String> delete(RequestDetails requestDetails,  Long id);

	CompletionStage<List> getByGeneric(RequestDetails requestDetails, Class clazz, String fieldName, Object value);

}


