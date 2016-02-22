package com.gnostrenoff.cdb.controllers.utils;

import java.util.List;

import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.dto.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;

public class PageCreator {
	
	public static PageDto create(List<Computer> computerList, QueryParams queryParams, int nbTotalElements){
		
		PageDto pageDto = new PageDto(queryParams.getIndex(), queryParams.getNbElements());
		
		int totalPages = (nbTotalElements / queryParams.getNbElements());
    	if(nbTotalElements % queryParams.getNbElements() != 0){
    		totalPages++;
    	}

    	pageDto.setNbTotalPages(totalPages);
    	pageDto.setComputerList(ComputerDtoMapper.toDtoList(computerList));
    	pageDto.setSearch(queryParams.getSearch());
    	pageDto.setOrderBy(queryParams.getOrderBy().toString());
		
		int pgStart = Math.max(queryParams.getIndex() - 5, 1);
        int pgEnd = pgStart + 10;
        if (pgEnd > totalPages) {
            int diff = pgEnd - totalPages;
            pgStart -= diff;
            if (pgStart < 1)
                pgStart = 1;
            pgEnd = totalPages;
        }
        
        pageDto.setPageStart(pgStart);
        pageDto.setPageEnd(pgEnd);
	
		return pageDto;
	}

}
