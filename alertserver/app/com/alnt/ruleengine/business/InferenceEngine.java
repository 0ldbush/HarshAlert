package com.alnt.ruleengine.business;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.alnt.policyengine.domain.dto.RuleConditionDTO;
import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.domain.dto.RuleFailedCause;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.libs.Json;

@Singleton
public abstract class InferenceEngine<INPUT_DATA, OUTPUT_RESULT> {

    @Inject
    private RuleParser<INPUT_DATA, OUTPUT_RESULT> ruleParser;

    /**
     * Run inference engine on set of rules for given data.
     * @param ruleDTOStream
     * @param inputData
     * @return
     */
    public OUTPUT_RESULT runSequential(RuleDTO rule, INPUT_DATA inputData){
    	
    	OUTPUT_RESULT result = null;
    	
        if (null == rule){
            return result;
        }

        
        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        boolean isMatched = matchSeq(rule, inputData);

     //   System.err.println(" IS MATHCED.." + isMatched);
        //STEP 2 (RESOLVE) : Resolve the conflict and give the selected one rule.
//        RuleDTO resolvedRule = resolve(conflictSet);
//        if (null == resolvedRule){
//            return null;
//        }

        //STEP 3 (EXECUTE) : Run the action of the selected rule on given data and return the output.
		if (isMatched) {
			ObjectMapper mapper = Json.mapper();

			@SuppressWarnings("unchecked")
			Map<String,Object> map = (Map<String, Object>) inputData;

			@SuppressWarnings("unchecked")
			Map<String, List<String>> requestMap = (Map<String, List<String>>) map.get("requestMap");
			@SuppressWarnings("unchecked")
			List<String> attributeList = (List<String>) map.get("attributeList");
			List<RuleConditionDTO> ruleConditionDTOs = null;

				try {
					ruleConditionDTOs = Arrays.asList(mapper.readValue(rule.getConditionJson(), RuleConditionDTO[].class));
				} catch (JsonProcessingException e) {
				//	e.printStackTrace();
				}
			
			Map<String, List<String>> attributeMap = new HashMap<String, List<String>>();
			Stack<List<RuleConditionDTO>> stack = new Stack<List<RuleConditionDTO>>();
			if (ruleConditionDTOs != null)
				stack.add(ruleConditionDTOs);
			while (!stack.isEmpty()) {
				List<RuleConditionDTO> conditionDTOs = stack.pop();
				for (RuleConditionDTO conditionDTO : conditionDTOs) {
					String attribute = conditionDTO.getAttribute();
					if (attributeList.contains(attribute)) {
						if (attributeMap.containsKey(attribute)) {
							attributeMap.get(attribute).addAll(conditionDTO.getValue());
						} else
							attributeMap.put(attribute, conditionDTO.getValue());
					}
					if (conditionDTO.getChildren() != null)
						stack.add(conditionDTO.getChildren());
				}
			}
			List<RuleFailedCause> cause = new ArrayList<RuleFailedCause>();
			for (String attribute : attributeMap.keySet()) {
				List<String> requestValueList = requestMap.get(attribute);
				List<String> ruleValueList = attributeMap.get(attribute);
				Set<String> intersectionList = requestValueList.stream().filter(ruleValueList::contains)
						.collect(Collectors.toSet());
				RuleFailedCause ruleFailedCause = new RuleFailedCause();
				ruleFailedCause.setAttribute(attribute);
				ruleFailedCause.setValue(intersectionList);
				cause.add(ruleFailedCause);
			}

			result = initializeOutputResult(rule, cause);
        	// result = executeRule(rule,inputData);

		}

        return result;
//        CompletionStage<Stream<OUTPUT_RESULT>> thenApplyAsync = conflictSet.thenApplyAsync(ruleDtoStream -> {
//        	return ruleDtoStream.map(rule -> executeRule(rule,inputData));
//        });
//        
//        return thenApplyAsync;
//        OUTPUT_RESULT outputResult = executeRule(resolvedRule, inputData);

//        return supplyAsync(() ->  outputResult);
        //return ;
    }
    
    /**
     * Run inference engine on set of rules for given data.
     * @param ruleDTOStream
     * @param inputData
     * @return
     */
    public CompletionStage<Stream<OUTPUT_RESULT>> run (Stream<RuleDTO> ruleDTOStream, INPUT_DATA inputData){
        if (null == ruleDTOStream){
            return null;
        }

        //STEP 1 (MATCH) : Match the facts and data against the set of rules.
        CompletionStage<Stream<RuleDTO>> conflictSet = match(ruleDTOStream, inputData);

        //STEP 2 (RESOLVE) : Resolve the conflict and give the selected one rule.
//        RuleDTO resolvedRule = resolve(conflictSet);
//        if (null == resolvedRule){
//            return null;
//        }

        //STEP 3 (EXECUTE) : Run the action of the selected rule on given data and return the output.
        CompletionStage<Stream<OUTPUT_RESULT>> thenApplyAsync = conflictSet.thenApplyAsync(ruleDtoStream -> {
        	return ruleDtoStream.map(rule -> executeRule(rule,inputData));
        });
        
        return thenApplyAsync;
//        OUTPUT_RESULT outputResult = executeRule(resolvedRule, inputData);

//        return supplyAsync(() ->  outputResult);
        //return ;
    }

    /**
     *We can use here any pattern matching algo:
     * 1. Rete
     * 2. Linear
     * 3. Treat
     * 4. Leaps
     *
     * Here we are using Linear matching algorithm for pattern matching.
     * @param ruleDTOStream
     * @param inputData
     * @return
     */
    protected CompletionStage<Stream<RuleDTO>>  match(Stream<RuleDTO> ruleDTOStream, INPUT_DATA inputData){
    	
    	return supplyAsync(() -> filterRules(ruleDTOStream, inputData));
                
    }
    
    protected boolean  matchSeq(RuleDTO ruleDTO, INPUT_DATA inputData){
    	
    	return filterRulesSeq(ruleDTO, inputData);
                
    }
    
    private boolean filterRulesSeq (RuleDTO rule, INPUT_DATA inputData) {
    	
				String condition = rule.getCondition();
				boolean matched = ruleParser.parseCondition(condition, inputData);
//				System.err.print(".");
				return matched;
		
    }
    
    private Stream<RuleDTO> filterRules (Stream<RuleDTO> ruleDTOStream, INPUT_DATA inputData) {
    	return ruleDTOStream.filter( (rule) -> {
				String condition = rule.getCondition();
				boolean matched = ruleParser.parseCondition(condition, inputData);
				return matched;
		});
    }

    /**
     * We can use here any resolving techniques:
     * 1. Lex
     * 2. Recency
     * 3. MEA
     * 4. Refactor
     * 5. Priority wise
     *
     *  Here we are using find first rule logic.
     * @param conflictSet
     * @return
     */
    protected RuleDTO resolve(CompletionStage<Stream<RuleDTO>> conflictSet){
    	
    	
		try {
			Optional<RuleDTO> rule = conflictSet.thenApply(data -> {return data.findFirst();}
																	).toCompletableFuture().get();
			
			 if (rule.isPresent()){
		            return rule.get();
		        }
		} catch (Exception e) {
			e.printStackTrace();
		} 
    
        return null;
    }

    /**
     * Execute selected rule on input data.
     * @param rule
     * @param inputData
     * @return
     */
    protected OUTPUT_RESULT executeRule(RuleDTO rule, INPUT_DATA inputData){
        OUTPUT_RESULT outputResult = initializeOutputResult(rule);
        return ruleParser.parseAction(rule.getAction(), inputData, outputResult);
    }
    protected OUTPUT_RESULT executeRule(RuleDTO rule, INPUT_DATA inputData,List<RuleFailedCause> cause){
        OUTPUT_RESULT outputResult = initializeOutputResult(rule,cause);
        return ruleParser.parseAction(rule.getAction(), inputData, outputResult);
    }

    protected abstract OUTPUT_RESULT initializeOutputResult(RuleDTO rule);
    protected abstract OUTPUT_RESULT initializeOutputResult(RuleDTO rule,List<RuleFailedCause> cause);
    protected abstract String getRuleNamespace();
}
