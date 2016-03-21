/*
 *
 */
package com.gnostrenoff.cdb.dto.mapper;

import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.dto.PageParams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

/**
 * The Class PageDtoMapper.
 */
@Component
public class PageDtoMapper {

  /** The computer dto mapper. */
  @Autowired
  private ComputerDtoMapper computerDtoMapper;

  /**
   * Converts a pageParams object into a PageDto.
   *
   * @param page the page
   * @param params the params
   * @return the page dto
   */
  public PageDto toPageDto(Page<Computer> page, PageParams params) {

    PageDto dto = new PageDto();

    int index = page.getNumber() + 1;
    int nbTotalPage = page.getTotalPages();

    dto.setComputerList(computerDtoMapper.toDtoList(page.getContent()));
    dto.setIndex(index);
    dto.setNbTotalPages(nbTotalPage);
    dto.setNbElements(page.getSize());
    dto.setNbTotalComputers(page.getTotalElements());

    int pgStart = Math.max(index - 5, 1);
    int pgEnd = pgStart + 10;
    if (pgEnd > nbTotalPage) {
      int diff = pgEnd - nbTotalPage;
      pgStart -= diff;
      if (pgStart < 1) {
        pgStart = 1;
      }
      pgEnd = nbTotalPage;
    }
    dto.setPageStart(pgStart);
    dto.setPageEnd(pgEnd);

    dto.setSearch(params.getSearch());
    dto.setOrder(params.getOrder());
    dto.setOrderBy(params.getOrderBy());

    return dto;
  }

}
