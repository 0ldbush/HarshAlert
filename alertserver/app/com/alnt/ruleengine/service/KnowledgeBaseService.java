package com.alnt.ruleengine.service;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.alnt.platform.base.request.RequestDetails;
import com.alnt.policyengine.domain.Rule;
import com.alnt.ruleengine.repository.RulesRepository;

import play.libs.concurrent.HttpExecutionContext;

@Service
public class KnowledgeBaseService {
	
	private RulesRepository rulesRepository;
    private HttpExecutionContext ec;
    

    @Inject
    public KnowledgeBaseService( HttpExecutionContext ec, RulesRepository repository) {
    	
        this.ec = ec;
        this.rulesRepository = repository;
    }
	

    public CompletionStage<List<Rule>> getAllRules(){
        return rulesRepository.list(new RequestDetails()).thenApplyAsync(dataStream -> {
            return dataStream.collect(Collectors.toList());
        }, ec.current());
    }

    public CompletionStage<List<Rule>> getAllRuleByNamespace(Long ruleNamespace){
    	
    	return rulesRepository.findByRuleNamespace(new RequestDetails(), ruleNamespace).thenApplyAsync(dataStream -> {
    		return dataStream.collect(Collectors.toList());
    	},ec.current());
     
    }

    
}
