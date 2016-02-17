package com.gnostrenoff.cdb.dto.mappers;

import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Page;

public class PageDtoMapper {
	
	public static PageDto toDto(Page page){
		
		PageDto pageDto = new PageDto(page.getIndex(), page.getNbElements());
		pageDto.setComputerList(ComputerDtoMapper.toDtoList(page.getComputerList()));
		
		return pageDto;
	}

}
