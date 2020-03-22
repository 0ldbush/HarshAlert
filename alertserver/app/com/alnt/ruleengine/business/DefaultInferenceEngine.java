package com.alnt.ruleengine.business;

import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import com.alnt.policyengine.domain.dto.RuleDTO;
import com.alnt.ruleengine.domain.dto.DefaultOutput;
import com.alnt.ruleengine.domain.dto.RuleFailedCause;

@Singleton
public class DefaultInferenceEngine extends InferenceEngine<Map, DefaultOutput> {

    @Override
    protected String getRuleNamespace() {
        return "DEFAULT";
    }

    @Override
    protected DefaultOutput initializeOutputResult(RuleDTO rule) {
        return new DefaultOutput(rule);
    }

	@Override
	protected DefaultOutput initializeOutputResult(RuleDTO rule, List<RuleFailedCause> cause) {
		return new DefaultOutput(rule, cause);
	}

	
}
