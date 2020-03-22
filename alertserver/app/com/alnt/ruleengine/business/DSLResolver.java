package com.alnt.ruleengine.business;

import java.io.Serializable;

public interface DSLResolver extends Serializable {
    String getResolverKeyword();
    Object resolveValue(String keyword);
}
