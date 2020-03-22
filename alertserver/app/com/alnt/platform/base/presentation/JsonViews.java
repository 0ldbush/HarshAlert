package com.alnt.platform.base.presentation;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;

public class JsonViews {
	
	public static class Combo {
		
    }

	public static class Header extends Combo {
		
    }
	
	public static class Medium extends Header {
		
    }
	
	public static class Full extends Medium {
		
    }
	
	public static class NONE extends Medium {
		
    }

    public static JsonNode toJson(Object bean, Class<?> view) {
        JsonNode result=null;
        try {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);
        	objectMapper.setSerializationInclusion(Include.NON_NULL);
        	objectMapper.setSerializationInclusion(Include.NON_EMPTY); 
            String json=objectMapper.writerWithView(view).writeValueAsString(bean);
            result =Json.parse(json);
        } catch (IOException e) { // should not occur, no real i/o...
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return  result;
    }
}
