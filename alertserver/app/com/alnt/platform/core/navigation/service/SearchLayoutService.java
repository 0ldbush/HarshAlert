package com.alnt.platform.core.navigation.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.navigation.domain.SearchLayout;
import com.alnt.platform.core.navigation.domain.dto.SearchLayoutDTO;
import com.alnt.policyengine.service.PolicyServiceImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(SearchLayoutServiceImpl.class)
public interface SearchLayoutService extends BaseService<SearchLayout, SearchLayoutDTO> {

}
