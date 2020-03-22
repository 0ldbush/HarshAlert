package com.alnt.platform.base.mapper;

import org.mapstruct.Mapping;

import com.alnt.platform.base.domain.Entity;
import com.alnt.platform.base.domain.dto.DTO;

public interface BaseMapper<D extends DTO, E extends Entity> {

	public E dtoToEntity(D dto);
	
	@Mapping(source = "id", target = "id_str")
	@Mapping(source = "id", target = "id")
	public D entityToDTO(E entity);
}
