package com.alnt.platform.application.logger.service;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

import com.alnt.platform.application.logger.domain.AppLog;
import com.alnt.platform.application.logger.domain.dto.AppLogDTO;
import com.alnt.platform.application.logger.mapper.AppLogMapper;
import com.alnt.platform.application.logger.repository.AppLogRepository;
import com.alnt.platform.base.request.Criteria;
import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.base.request.SearchCriteria;
import com.alnt.platform.base.service.BaseServiceImpl;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class AppLogServiceImpl extends BaseServiceImpl<AppLog, AppLogDTO> implements AppLogService {
    
	@Inject
	public AppLogServiceImpl(HttpExecutionContext ec, AppLogRepository repository) {
		super( ec, repository, AppLogMapper.INSTANCE);
	}

	@Override
	public CompletionStage<Optional<AppLogDTO>> save(RequestDetails requestDetails, AppLogDTO data) {
		if(StringUtils.isNotBlank(data.getStage()) && data.getBusObjId()!=null && StringUtils.isNotBlank(data.getBusObjCat()))
		{
		SearchCriteria searchCriteria=new SearchCriteria();
		Criteria objCriteria=new Criteria("busObjCat",data.getBusObjCat());
		Criteria objIdCriteria=new Criteria("busObjId",data.getBusObjId().toString());
		Criteria stageCriteria=new Criteria("stage",data.getStage());
		searchCriteria.getFilterCriteria().add(objCriteria);
		searchCriteria.getFilterCriteria().add(objIdCriteria);
		searchCriteria.getFilterCriteria().add(stageCriteria);
		return this.getDaoRepository().findAll(requestDetails, searchCriteria).thenComposeAsync(page->{
			data.setId(page==null || page.toList().isEmpty()?null:page.toList().get(0).getId());
			return super.save(requestDetails, data);
			},ec.current()
		);
		}
		else return super.save(requestDetails, data);
	}

}
