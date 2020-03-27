package com.alnt.ruleengine.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.platform.core.configsetting.domain.dto.ConfigSettingDTO;
import com.alnt.platform.core.configsetting.service.ConfigSettingLocalCachedServiceImpl;
import com.alnt.platform.core.configsetting.service.ConfigSettingService;
import com.alnt.platform.core.configsetting.service.ConfigSettingServiceImpl;
import com.alnt.policyengine.domain.Rule;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.service.RuleEngineService;
import com.jayway.jsonpath.DocumentContext;

import play.libs.concurrent.HttpExecutionContext;

@Singleton
public class JSONManipulator {

	
	ConfigSettingService configSettingService;
	
	@Inject
    public JSONManipulator(@Named("localcache") ConfigSettingService cs) {
		
		this.configSettingService = cs;
	}
	
	
	public  String applyConfigToJSON(RequestDetails requestDetails,String json ) {
		
				

		try {
			
			CompletionStage<List<ConfigSettingDTO>> byCached = ((ConfigSettingLocalCachedServiceImpl)configSettingService).getByCached(requestDetails, "groupName", "SOD");

			
			List<ConfigSettingDTO> list = byCached.toCompletableFuture().get();
			
			List<ConfigSettingDTO> collect = list.stream().filter(e -> {
				return  e.getExtId().equals("marked_for_deletion_expr");
			})
			.collect(Collectors.toList());
			
			for(ConfigSettingDTO c : collect) {
				 json = applyConfig(json,  c.getValue());
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		//marked_for_deletion_expr
		return json;
	}
	
	private static String applyConfig(String json,String setting) {
		
		try {
			
			DocumentContext jsonDocContext = com.jayway.jsonpath.JsonPath.parse(json);
			DocumentContext delete = jsonDocContext.delete("$." + setting);
			return delete.jsonString();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return json;
		
	       
	    
	}
	private static boolean manipulate_Request_Against_Rule(String rule, Object request) {
		
		String[] splitTexts = rule.split("\\.");
		String split = rule.substring(rule.indexOf(".") + 1); //a.split(".");
		while(splitTexts.length > 1) {
			
			String key = splitTexts[0];
			
			if(request instanceof ArrayList) {
				List arList = ((List)request);
				manipulate_Request_Against_Rule(split,arList);
				
			}else if(request instanceof Map) {
				
				Object object = ((Map)request).get(key);
				manipulate_Request_Against_Rule(split,object);
			}
			
			
			//String split1 = split.substring(0,split.indexOf(".") ); //a.split(".");
			
		}
		
		String[] leafSplit = split.split("=");
		
		String val = null;
		if(request instanceof ArrayList) {
			List arList = ((List)request);
			for(Object ar : arList) {
				
				System.out.println("ar " + ar);
				
				if(ar instanceof Map) {
					 val = ((Map)ar).get(leafSplit[0]).toString();
				} else {
					val = (String) ar;
				}
				System.out.println(val);
				if(val != null  && val.equals(leafSplit[1].trim())) return true;
				
				return false;
				
			}
			
		} else if(request instanceof Map) { 
			
			 val = ((Map)request).get(leafSplit[0]).toString();
			
		}
		
		System.out.println(leafSplit[0]);
		System.out.println(leafSplit[1].trim().equals(val));
		
		return leafSplit[1].trim().equals(val);
	}
}
