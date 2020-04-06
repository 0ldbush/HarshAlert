package com.alnt.ruleengine.business;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import javax.inject.Singleton;

import org.mvel2.MVEL;
import org.mvel2.ParserConfiguration;
import org.mvel2.ParserContext;

import com.github.benmanes.caffeine.cache.Caffeine;

@Singleton
public class MVELParser<INPUT_DATA> {

//    public boolean parseMvelExpression( String expression, INPUT_DATA inputObjects){
//        try {
//            return MVEL.evalToBoolean(expression,inputObjects);
//        }catch (Exception e){
//        	
//        	System.out.println(e.getStackTrace());
//        	e.printStackTrace();
//            //log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
//        }
//        return false;
//    }
	
	private static Cache<String, Serializable> compiledExpressionCache = Caffeine.newBuilder()
			  .expireAfterWrite(15, TimeUnit.MINUTES)
			  .maximumSize(20000)
			  .build();
	private  ParserContext parserCcontext ;
	
	
	public MVELParser() {
		super();
    	if(parserCcontext == null) {
    		ParserConfiguration parserConfiguration = new ParserConfiguration();
    		parserConfiguration.setClassLoader(Thread.currentThread().getContextClassLoader());
    		parserCcontext = new ParserContext(parserConfiguration);
    	}
    }
	
	
    public boolean parseMvelExpression( String expression, INPUT_DATA inputObjects){
        try {
        	
//        	Object executeExpression =  MVEL.eval(expression.toCharArray(),inputObjects);
//        	Object executeExpression =  MVEL.eval(expression,inputObjects);
        	Serializable compiledExpression = compiledExpressionCache.get(expression, (key) -> {
        		return MVEL.compileExpression(expression, parserCcontext);
        	});

    		Object executeExpression = MVEL.executeExpression(compiledExpression, inputObjects);

            return executeExpression != null ? (Boolean)executeExpression : false;
        }catch (Exception e){
        	
        	System.out.println(e.getStackTrace());
        	e.printStackTrace();
        	throw new RuntimeException(e.getMessage());
            //log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
        }
       // return false;
    }
    
    
}
