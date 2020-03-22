package com.alnt.ruleengine.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONManipulator {

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
