package com.alnt.ruleengine.business;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

import com.alnt.policyengine.domain.dto.RuleDTO;

@Singleton
public class RuleParser<INPUT_DATA, OUTPUT_RESULT> {

    @Inject
    protected DSLParser dslParser;
    @Inject
    protected MVELParser mvelParser;

    private final String INPUT_KEYWORD = "input";
    private final String OUTPUT_KEYWORD = "output";
    

    /*
     * Parsing in given priority/steps.
     *
     * Step 1. Resolve domain specific keywords first: $(rulenamespace.keyword)
     * Step 2. Resolve MVEL expression.
     *
     * @param expression
     * @param inputData
     */
    public boolean parseCondition(RuleDTO rule, INPUT_DATA inputData) {
    	
    	if(StringUtils.isBlank(rule.getCondition())) return false;
       // String resolvedDslExpression = dslParser.resolveDomainSpecificKeywords(expression);
        String resolvedDslExpression = rule.getCondition();
        Map<String, Object> input = new HashMap<>();
        input.put(INPUT_KEYWORD, inputData);
       
        boolean match = mvelParser.parseMvelExpression(rule, inputData);
        return match;
    }

    /**
     * Parsing in given priority/steps.
     *
     * Step 1. Resolve domain specific keywords: $(rulenamespace.keyword)
     * Step 2. Resolve MVEL expression.
     *
     * @param expression
     * @param inputData
     * @param outputResult
     * @return
     */
    public OUTPUT_RESULT parseAction(String expression, INPUT_DATA inputData, OUTPUT_RESULT outputResult) {
    	
    	if(StringUtils.isBlank(expression)) return outputResult;
      //  String resolvedDslExpression = dslParser.resolveDomainSpecificKeywords(expression);
    	String resolvedDslExpression = expression;
        Map<String, Object> input = new HashMap<>();
        input.put(INPUT_KEYWORD, inputData);
        input.put(OUTPUT_KEYWORD, outputResult);
        mvelParser.parseMvelExpression(resolvedDslExpression, input);
        return outputResult;
    }

}
