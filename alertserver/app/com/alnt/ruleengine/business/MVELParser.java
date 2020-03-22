package com.alnt.ruleengine.business;

import java.io.Serializable;

import javax.inject.Singleton;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
    
    public boolean parseMvelExpression( String expression, INPUT_DATA inputObjects){
        try {
        	
        	Object executeExpression =  MVEL.eval(expression,inputObjects);
        	//ParserContext context = new ParserContext();
    		//Serializable compiledExpression = MVEL.compileExpression(expression, context);
    		
    		//Object executeExpression = MVEL.executeExpression(compiledExpression, inputObjects);

            return (Boolean)executeExpression;
        }catch (Exception e){
        	
        	System.out.println(e.getStackTrace());
        	e.printStackTrace();
        	throw new RuntimeException(e.getMessage());
            //log.error("Can not parse Mvel Expression : {} Error: {}", expression, e.getMessage());
        }
       // return false;
    }
    
    
}
