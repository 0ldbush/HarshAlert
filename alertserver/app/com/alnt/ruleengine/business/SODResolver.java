package com.alnt.ruleengine.business;

import javax.inject.Singleton;

@Singleton
public class SODResolver implements DSLResolver {
  
	private static final long serialVersionUID = 1L;
	
	private static final String RESOLVER_KEYWORD = "SOD";
    private static final String CHECK_MITIGATION = "CHECK_MITIGATION";
    private static final String IS_ACTIVE = "isActive";

    @Override
    public String getResolverKeyword() {
        return RESOLVER_KEYWORD;
    }

    @Override
    public Object resolveValue(String keyword) {
        if (keyword.equalsIgnoreCase(CHECK_MITIGATION)){
            //Code to calculate if this is mitigated
            return false;
        }

        if (keyword.equalsIgnoreCase(IS_ACTIVE)){
            //Code to see if it is active or not
            return true;
        }

        return null;
    }
}