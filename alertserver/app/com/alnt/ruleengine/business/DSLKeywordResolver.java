package com.alnt.ruleengine.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DSLKeywordResolver {
	
    private Map<String, DSLResolver> dslKeywordResolverList = null;

    @Inject
    public DSLKeywordResolver( SODResolver resolver) {
    	List<DSLResolver> r = new ArrayList<DSLResolver>();
    	r.add(resolver);
        dslKeywordResolverList = r.stream()
                .collect(Collectors.toMap(DSLResolver::getResolverKeyword, Function.identity()));
    }
    
//    @Inject
//    public DSLKeywordResolver( List<DSLResolver> resolverList) {
//        dslKeywordResolverList = resolverList.stream()
//                .collect(Collectors.toMap(DSLResolver::getResolverKeyword, Function.identity()));
//    }

    public Map<String, DSLResolver> getDslKeywordResolverList(){
        return dslKeywordResolverList;
    }

    public Optional<DSLResolver> getResolver(String keyword) {
        return Optional.ofNullable(dslKeywordResolverList.get(keyword));
    }
}
