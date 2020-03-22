package com.alnt.ruleengine.business;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RuleLibrary {

	public static boolean ANYTWO(List<Map> listOfStrings, List<String> list) {
        System.out.println("I am inside method ANYTWO");
        int count = 0;
        List<Object> collect = listOfStrings.stream().map(( m ) -> m.get("roleName")).collect(Collectors.toList());
        List<Boolean> collect2 = list.stream().map((e) -> {
        	for (Object s : collect) {
            	String ss = (String) s;
            	if(ss.equals(e)) {
            		return true;
            	}
            }
        	return false;
        }).collect(Collectors.toList());
        
        for(Boolean b : collect2) {
        	if(b)  {
        		count++;
        	}
        }
        if(count >= 2) return true;
        	
        return false;
       
    }
	
	public static boolean ANYTWO(List<Map> listOfStrings, String name, List<String> list) {
        System.out.println("I am inside method ANYTWO");
        int count = 0;
        List<Object> collect = listOfStrings.stream().map(( m ) -> m.get(name)).collect(Collectors.toList());
        List<Boolean> collect2 = list.stream().map((e) -> {
        	for (Object s : collect) {
            	String ss = (String) s;
            	if(ss.equals(e)) {
            		return true;
            	}
            }
        	return false;
        }).collect(Collectors.toList());
        
        for(Boolean b : collect2) {
        	if(b)  {
        		count++;
        	}
        }
        if(count >= 2) return true;
        	
        return false;
       
    }
	
	public static boolean ANYM(List<Map> listOfStrings, String name, List<String> list, int max) {
        System.out.println("I am inside method ANYTM");
        
        int count = 0;
        
        List<Object> collect = listOfStrings.stream().map(( m ) -> m.get(name)).collect(Collectors.toList());
        
        List<Boolean> collect2 = list.stream().map((e) -> {
        	for (Object s : collect) {
            	String ss = (String) s;
            	if(ss.equals(e)) {
            		return true;
            	}
            }
        	return false;
        }).collect(Collectors.toList());
        
        for(Boolean b : collect2) {
        	if(b)  {
        		count++;
        	}
        }
        if(count >= max) return true;
        	
        return false;
       
    }
	
	public static boolean CONFLICT_IF_ALL(List<Map> listOfStrings, List<String> list) {
        System.out.println("I am inside method ANYTWO");
        
        List<Object> collect = listOfStrings.stream().map(( m ) -> m.get("roleName")).collect(Collectors.toList());
        
        List<Boolean> collect2 = list.stream().map((e) -> {
        	for (Object s : collect) {
            	String ss = (String) s;
            	if(ss.equals(e)) {
            		return true;
            	}
            }
        	return false;
        }).collect(Collectors.toList());
        
        if(collect.size() != list.size())  return false;	//size is different, means all not matching
        
        for(Boolean b : collect2) {
        	if(!b) return false;
        }
        	
        return true;
       
    }
}
