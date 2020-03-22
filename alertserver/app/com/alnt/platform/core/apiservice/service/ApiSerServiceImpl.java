package com.alnt.platform.core.apiservice.service;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;

import com.alnt.platform.base.service.BaseServiceImpl;
import com.alnt.platform.base.util.MvelHelper;
import com.alnt.platform.core.apiservice.domain.ApiService;
import com.alnt.platform.core.apiservice.domain.dto.ApiServiceDTO;
import com.alnt.platform.core.apiservice.domain.dto.ApiServiceParameterDTO;
import com.alnt.platform.core.apiservice.mapper.ApiServiceMapper;
import com.alnt.platform.core.apiservice.repository.ApiServiceRepository;
import com.alnt.platform.core.apiservice.service.type.ApiServiceType;
import com.alnt.platform.core.messagemaster.domain.MessageLog;
import com.alnt.workflow.domain.Workflow;
import com.google.common.base.Strings;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class ApiSerServiceImpl extends BaseServiceImpl<ApiService, ApiServiceDTO> implements ApiSerService {
    
	@Inject
	public ApiSerServiceImpl(HttpExecutionContext ec, ApiServiceRepository repository) {
		super( ec, repository, ApiServiceMapper.INSTANCE);
	}
	

	@Override
	public Object invokeApi(String apiName, Map params) {
		ApiServiceDTO apiService = getByApiServiceCode(apiName);
		if (apiService == null || !apiService.isActive()) {
			throw new RuntimeException("Service :" + apiName + " is not present in Service collection");
		}
		if (ApiServiceType.METHOD.getType().equals(apiService.getType().getType()) && (apiService.getClazz() == null || "".equals(apiService.getClazz()) || apiService.getMethod() == null
				|| "".equals(apiService.getMethod()))) {
			throw new RuntimeException("Service :" + apiName + " doesn't contain class && method");
		}
		if (ApiServiceType.BUSINESS_RULE.getType().equals(apiService.getType().getType()) && (apiService.getMatchRule() == null)) {
			throw new RuntimeException("Match Rule is null for Api : "+ apiName);
		}
		if (ApiServiceType.BUSINESS_RULE.getType().equals(apiService.getType().getType())){
			return invokeBusinessRuleInternal(apiService, params);
		}  else {
			String className = apiService.getClazz();
			String methodName = apiService.getMethod();
	
			Class[] paramTypes = this.getParamTypesForInterfaceMethod(className, methodName);
			Set<Map.Entry<String, Object>> paramsJEMap = params != null ? params.entrySet() : null;
			
			boolean anyConstant = false;
			for(ApiServiceParameterDTO apiParam : apiService.getApiServiceParameters()) {
				if(!Strings.isNullOrEmpty(apiParam.getConstantValue())){
					anyConstant = true;
				}
			}
			if (!anyConstant && params != null && paramTypes.length > 0 && paramsJEMap.size() < paramTypes.length)
				throw new RuntimeException("Please pass in the right number of parameters to this method:" + methodName
						+ ". It needs to receive " + paramTypes.length + " parameters.");
	
			Object[] paramsArray = new Object[paramTypes.length];
			
			int i = 0;
			for(ApiServiceParameterDTO apiParam : apiService.getApiServiceParameters()) {
				if(!Strings.isNullOrEmpty(apiParam.getField())) {
					Object parVal = params.get(apiParam.getField());
					if(parVal == null && !Strings.isNullOrEmpty(apiParam.getConstantValue())) {
						parVal = apiParam.getConstantValue();
					}
					paramsArray[i] = parVal;
					i++;
				}
			}
	
			return this.invokeDirect(className, methodName, paramsArray);
		}
	}
	
	@Override
	public Object invokeBusinessRules(Map<String, Map> rules) {
		rules.forEach((key,value) -> invokeBusinessRule(key, value));
		return null;
	}
	
	@Override
	public boolean invokeBusinessRule(String ruleApi, Map params) {
		ApiServiceDTO apiService = getByApiServiceCode(ruleApi);
		if (apiService == null || !apiService.isActive()) {
			throw new RuntimeException("Service :" + ruleApi + " is not present in Service collection");
		}
		if (!ApiServiceType.BUSINESS_RULE.getType().equals(apiService.getType().getType())) {
			throw new RuntimeException("Service :" + ruleApi + " is not a business rule");
		}
		if (ApiServiceType.BUSINESS_RULE.getType().equals(apiService.getType().getType()) && (apiService.getMatchRule() == null)) {
			throw new RuntimeException("Match Rule is null for Api : "+ ruleApi);
		}
		return invokeBusinessRuleInternal(apiService, params);
	}
	
	private Boolean invokeBusinessRuleInternal(ApiServiceDTO apiService, Map<String, Object> ruleParameters){
		Boolean pass = Boolean.FALSE;
		boolean allParametersPassed = true;
		if(apiService.getApiServiceParameters() != null && !apiService.getApiServiceParameters().isEmpty()) {
			for(ApiServiceParameterDTO apiParam : apiService.getApiServiceParameters()) {
				if(!ruleParameters.containsKey(apiParam.getField())) {
					allParametersPassed = false;
				}
			}
		} 
		if(!allParametersPassed) {
			throw new RuntimeException("All the required parameters not passed for matching rule : Rule Name : "+ apiService.getName());
		}
//		LOG.debug("Matching bus rule for : " + apiService.getMatchRule() + " ");
		pass = MvelHelper.evaluateRule(apiService.getMatchRule(), ruleParameters);
//		LOG.debug("Matching bus rule for : " + apiService.getMatchRule() + "  match pass : "+ pass);
		return pass;
	}
	
	private Class[] getParamTypesForInterfaceMethod(String interfaceName, String methodName) {
		Class apiInterfaceClass = null;
		if (interfaceName.indexOf(".") < 0) {
			apiInterfaceClass = this.getApiInterfaceClass(interfaceName);
		} else {
			apiInterfaceClass = getClass(interfaceName);
		}

		// first track down the method in the interface in order to get the parameter
		// types
		Method[] methods = apiInterfaceClass.getMethods();
		Method method = null;
//		LOG.debug("Searching for the method:" + methodName + " in interface:" + interfaceName);
		for (Method m : methods) {
			if (m.getName().equals(methodName)) {
				method = m;
				break;
			}
		}

		if (method == null) {
//			LOG.info("The method:" + methodName + " in interface:" + interfaceName + " was not found");
			throw new RuntimeException("Could not find method:" + methodName + " in interface:" + interfaceName);
		}

		// get param types from the method in the interface
		Class[] paramTypes = method.getParameterTypes();

		return paramTypes;
	}

	private Object invokeDirect(String interfaceName, String methodName, Object[] paramsArray) {
		Object retVal = null;

		try {
			Class apiInterfaceClass = this.getApiInterfaceClass(interfaceName);
			Class[] paramTypes = this.getParamTypesForInterfaceMethod(interfaceName, methodName);

			// now get the implementation
			ApplicationContext context = (ApplicationContext) Class.forName("com.minidukan.core.application.config.AppConfig").getDeclaredMethod("getContext").invoke(null);
			
			Object service = (Object)  context.getBean(apiInterfaceClass);

			// now get method from implementation
			Method m = service.getClass().getMethod(methodName, paramTypes);

			// now invoke the method
			retVal = m.invoke(service, paramsArray);

		} catch (Throwable th) {
			/*ErrorType errorType = ErrorType.API_SERVICE_ERROR;
			if(th instanceof InvocationTargetException) {
				Throwable ex = ((InvocationTargetException) th).getTargetException();
	        	if (ex instanceof BusinessException) {
	        		BusinessException bException = (BusinessException) ex;
	    			if(bException.getErrorType() !=null) {
	    				errorType = bException.getErrorType();
	    			}
	    		}
	            throw new ApiServiceException( errorType, ex);
			}else {
				throw new ApiServiceException( errorType, th);
			}*/
		}
		return retVal;
	}

	private Class getApiInterfaceClass(String interfaceName) {
		String fullClassName = null;
		fullClassName = interfaceName;
		return getClass(fullClassName);
	}

	private Class getClass(String className) {
		Class clazz = null;

		try {
			clazz = Class.forName(className);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

		return clazz;
	}

	@Override
	@Cacheable
	public List<ApiServiceDTO> getApisForQueue(String fromQueueName) {
		List<ApiService> apis = new ArrayList<ApiService>();
		//List<ApiService> apis = apiServiceDao.findByFromQueueName(fromQueueName);
		
		List<ApiServiceDTO> dtos = new ArrayList<ApiServiceDTO>();
		for(ApiService e : apis) {
			dtos.add(this.getMapper().entityToDTO(e));
		}
		return dtos;
	}
	
	
	@Override 
	@Cacheable
	public ApiServiceDTO getByApiServiceCode(String apiServiceCode) {
		
		CompletionStage<List<ApiService>> apiServiceGet = this.getDaoRepository().getBy(null, "apiServiceCode", apiServiceCode);
		

		ApiService apiSer = null;
		try {
			List<ApiService> apiServiceList = apiServiceGet.toCompletableFuture().get();
			if (apiServiceList.size() > 0) {
				apiSer = apiServiceList.get(0);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return (ApiServiceDTO)getMapper().entityToDTO(apiSer);
	}

}
