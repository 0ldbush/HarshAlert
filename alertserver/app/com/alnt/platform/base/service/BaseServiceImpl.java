package com.alnt.platform.base.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Nullable;
import javax.inject.Inject;

import org.springframework.data.domain.Page;

import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;
import com.alnt.platform.base.mapper.BaseMapper;
import com.alnt.platform.base.repository.BaseRepository;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.response.ApiResponse;
import com.typesafe.config.ConfigFactory;

import play.cache.NamedCache;
import play.cache.AsyncCacheApi;
import play.libs.concurrent.HttpExecutionContext;




public abstract class BaseServiceImpl<E extends Entity, D extends DTO> implements BaseService<E,D> {

	protected BaseRepository<E> repository;
	protected BaseMapper<D,E> mapper;
    protected HttpExecutionContext ec;
    
    @com.google.inject.Inject(optional = true)
    @NamedCache("play")
    private AsyncCacheApi cache;
    
    @com.google.inject.Inject(optional = true)
    @NamedCache("redis")
    private AsyncCacheApi redisCache;
   
    static final int MAX_SIZE = 1000;
    
    List<String> cacheConfig;
    
    public BaseServiceImpl( HttpExecutionContext ec, BaseRepository<E> repository, BaseMapper<D,E> mapper) {
        this.ec = ec;
        this.repository = repository;
        this.mapper = mapper;
        this.cacheConfig= ConfigFactory.load().getStringList("play.cache.enabled.entities");
       
    }
    
	public BaseRepository<E> getDaoRepository() {
		return repository;
	}

	public BaseMapper<D, E> getMapper() {
		return mapper;
	}

	
	@Override
	public CompletionStage<Stream<D>> find(RequestDetails requestDetails, SearchCriteria searchCriteria) {
		return this.getDaoRepository().list(requestDetails).thenApplyAsync(dataStream -> {
            return dataStream.map(entity -> getMapper().entityToDTO(entity));
        }, ec.current());
	}
	
	@Override
	public CompletionStage<ApiResponse> findAll(RequestDetails requestDetails, SearchCriteria searchCriteria) {
		if(searchCriteria == null) {
			throw new IllegalArgumentException("INVALID_ARGS");
		}
		if(MAX_SIZE < searchCriteria.getSize() || searchCriteria.getSize() == 0) {
			throw new IllegalArgumentException("INVALID_PAGESIZE");
		}
		if(searchCriteria != null && searchCriteria.getPage() > 0) {
			searchCriteria.setPage(searchCriteria.getPage()-1);
		}
		return this.getDaoRepository().findAll(requestDetails, searchCriteria).thenApplyAsync(data -> {
            return this.buildResponseFromPageeable((Page<E>)data);
        }, ec.current());
	}

//	@Override
//	public CompletionStage<D> create(RequestDetails requestDetails, D data) {
//		return this.getDaoRepository().create(requestDetails, getMapper().dtoToEntity(data)).thenApplyAsync((savedData) -> {
//            return getMapper().entityToDTO(savedData);
//        }, ec.current());
//	}

	@Override
	public CompletionStage<Optional<D>> get(RequestDetails requestDetails, Long id) {
		
	String cacheKeyPrefix=repository.getDomainClass().getSimpleName();
	if(cacheConfig!=null && cacheConfig.contains(cacheKeyPrefix))
	{
		return getCache().getOrElseUpdate(cacheKeyPrefix+"_get_"+id.toString(),() -> {
			return this.getDaoRepository().get(requestDetails, id).thenApplyAsync(optionalData -> {
				 return Optional.of(getMapper().entityToDTO(optionalData.get()));
	        }, ec.current());
		});
	}else
		
		
		return this.getDaoRepository().get(requestDetails, id).thenApplyAsync(optionalData -> {
            return Optional.of(getMapper().entityToDTO(optionalData.get()));
        }, ec.current());
	}
	
	@Override
	public CompletionStage<Stream<D>> getBy(RequestDetails requestDetails, String fieldName, Object value) {
	
		String cacheKeyPrefix=repository.getDomainClass().getSimpleName();
		if (cacheConfig != null && cacheConfig.contains(cacheKeyPrefix)) {
			 return getCache().getOrElseUpdate(cacheKeyPrefix+"_getBy_"+fieldName.toString()+"_"+value.toString(),() -> {
				CompletionStage<List<D>> thenApplyAsync = this.getDaoRepository().getBy(requestDetails, fieldName, value).thenApplyAsync(dataList -> {
					return dataList.stream().map(entity -> getMapper().entityToDTO(entity)).collect(Collectors.toList());
		        }, ec.current());
				
				return thenApplyAsync;
			}).thenApplyAsync(str -> { return str.stream();});
		} else
			return this.getDaoRepository().getBy(requestDetails, fieldName, value).thenApplyAsync(dataList -> {
				return dataList.stream().map(entity -> getMapper().entityToDTO(entity));
			}, ec.current());
	}

//	@Override
//	public CompletionStage<Optional<D>> update(RequestDetails requestDetails, Long id, D data) {
//		return this.getDaoRepository().update(requestDetails, id, getMapper().dtoToEntity(data)).thenApplyAsync(optionalData -> {
//			return Optional.of(getMapper().entityToDTO(optionalData.get()));
//        }, ec.current());
//	}
	
	@SuppressWarnings("unchecked")
	@Override
	public CompletionStage<Optional<D>> save(RequestDetails requestDetails, D data) {
		
		String cacheKeyPrefix=repository.getDomainClass().getSimpleName();
		if(cacheConfig!=null && cacheConfig.contains(cacheKeyPrefix) && cache != null  && data.getId() != null)
		{
			getCache().remove(cacheKeyPrefix+"_get_"+data.getId().toString());
		} 
		return this.getDaoRepository().save(requestDetails,  getMapper().dtoToEntity(data)).thenApplyAsync(optionalData -> {
			this.afterSave(Arrays.asList(optionalData));
			return Optional.of(getMapper().entityToDTO(optionalData.get()));
        }, ec.current());
	}
	
	@Override
	public CompletionStage<Optional<E>> saveEntity(RequestDetails requestDetails, E entity) {
		return this.getDaoRepository().save(requestDetails, entity).thenApplyAsync(optionalData -> {
			return optionalData;
        }, ec.current());
	}
	
	protected ApiResponse buildResponseFromPageeable(Page<E> page) {
		List<E> entities = page.getContent();
		List<D> dtos = entities.stream().map(entity -> this.getMapper().entityToDTO(entity)).collect(Collectors.toList());
		/*List<D> dtos = new ArrayList<D>();
		for(E e : entities) {
			dtos.add(this.getMapper().entityToDTO(e));
		}*/
		ApiResponse response  = new ApiResponse(true, dtos, null);
		response.setNumberOfElements(page.getNumberOfElements());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setPageNumber(page.getPageable().getPageNumber());
		response.setPageSize(page.getPageable().getPageSize());
		return response;
	}

	@Override
	public CompletionStage<List<Optional<D>>> saveAll(RequestDetails requestDetails,List<D> dataList) {
		List<E> entityList = dataList.stream().map(data -> this.getMapper().dtoToEntity(data)).collect(Collectors.toList());
		return this.getDaoRepository().saveAll(requestDetails,entityList).thenApplyAsync(optionalData -> {
			this.afterSave(optionalData);
			return optionalData.stream().map(entity -> Optional.of(this.getMapper().entityToDTO(entity.get()))).collect(Collectors.toList());
        }, ec.current());
	}
	
	@Override
	public CompletionStage<String> delete(RequestDetails requestDetails, Long id) {
		return this.getDaoRepository().delete(requestDetails, id).thenApplyAsync(optionalData -> {
            return optionalData;
        }, ec.current());
	}
	@SuppressWarnings("unchecked")
	@Override
	public void afterSave(List<Optional<E>> optionals) {
		
	}

	public AsyncCacheApi getCache() {
		return cache;
//		return redisCache != null ? redisCache : cache;
	}
}
