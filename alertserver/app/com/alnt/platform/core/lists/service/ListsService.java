package com.alnt.platform.core.lists.service;

import com.alnt.platform.base.service.BaseService;
import com.alnt.platform.core.lists.domain.Lists;
import com.alnt.platform.core.lists.domain.dto.ListsDTO;
import com.google.inject.ImplementedBy;

@ImplementedBy(ListsServiceImpl.class)
public interface ListsService extends BaseService<Lists, ListsDTO> {

}
