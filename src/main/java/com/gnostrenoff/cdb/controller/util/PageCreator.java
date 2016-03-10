package com.gnostrenoff.cdb.controller.util;

import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;

import java.util.List;

/**
 * The Class PageCreator.
 */
public class PageCreator {

  /**
   * Creates the.
   *
   * @param computerList
   *          the computer list
   * @param queryParams
   *          the query params
   * @return the page dto
   */
  public static PageDto create(List<Computer> computerList, QueryParams queryParams) {

    PageDto pageDto = new PageDto(queryParams.getIndex(), queryParams.getNbElements());

    int nbComputers = queryParams.getNbTotalComputers();
    int totalPages = (nbComputers / queryParams.getNbElements());
    if (nbComputers % queryParams.getNbElements() != 0) {
      totalPages++;
    }

    pageDto.setNbTotalPages(totalPages);
    pageDto.setNbTotalComputers(nbComputers);
    pageDto.setComputerList(ComputerDtoMapper.toDtoList(computerList));
    pageDto.setSearch(queryParams.getSearch());
    pageDto.setOrderBy(queryParams.getOrderBy().toString());
    pageDto.setOrder(queryParams.getOrder());

    int pgStart = Math.max(queryParams.getIndex() - 5, 1);
    int pgEnd = pgStart + 10;
    if (pgEnd > totalPages) {
      int diff = pgEnd - totalPages;
      pgStart -= diff;
      if (pgStart < 1) {
        pgStart = 1;
      }

      pgEnd = totalPages;
    }

    pageDto.setPageStart(pgStart);
    pageDto.setPageEnd(pgEnd);

    return pageDto;
  }

}
