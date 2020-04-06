package com.alnt.ruleengine.business;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.mvel2.MVEL;
import org.mvel2.ParserContext;

import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.policyengine.service.RuleService;

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
	
    @Inject
	private  RuleService ruleService ;
	
	
	public MVELParser() {
		super();
    }
	
	
    public boolean parseMvelExpression( RuleDTO rule, INPUT_DATA inputObjects){
        try {
        	Serializable compiledExpression = (Serializable) ruleService.getCompiledExpressionCache().get(rule.getId().toString(), (key) -> {
        		return MVEL.compileExpression(rule.getCondition(), (ParserContext)ruleService.getCompiledExpressionCache().getIfPresent("parserContext"));
        	});

    		Object executeExpression = MVEL.executeExpression(compiledExpression, inputObjects);
            return executeExpression != null ? (Boolean)executeExpression : false;
        }catch (Exception e){
        	System.out.println(e.getStackTrace());
        	e.printStackTrace();
        	throw new RuntimeException(e.getMessage());
        }
    }
    
    public boolean parseMvelExpression( String  expression, INPUT_DATA inputObjects){
        try {
        	
        	Object executeExpression =  MVEL.eval(expression,inputObjects);
            return executeExpression != null ? (Boolean)executeExpression : false;
        }catch (Exception e){
        	
        	System.out.println(e.getStackTrace());
        	e.printStackTrace();
        	throw new RuntimeException(e.getMessage());
        }
    } 
    
    
}
