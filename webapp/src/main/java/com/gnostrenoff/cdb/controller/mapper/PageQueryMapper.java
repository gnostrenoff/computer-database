package com.gnostrenoff.cdb.controller.mapper;

import com.gnostrenoff.cdb.controller.exception.InvalidRequestException;
import com.gnostrenoff.cdb.dto.PageParams;
import com.gnostrenoff.cdb.dto.QueryParams;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

/**
 * The Class PageQueryMapper.
 */
public class PageQueryMapper {

  /**
   * Converts a pageParmas object into a QueryParams object.
   *
   * @param pageParams
   *          the page params
   * @return the query params
   */
  public static QueryParams toQueryParams(PageParams pageParams) {

    // get page index and size
    int index = pageParams.getIndex() - 1;
    int nbElements = pageParams.getNbElements();

    // get order parameter
    String order = pageParams.getOrder();
    Direction direction;
    if ("ASC".equals(order)) {
      direction = Direction.ASC;
    } else if ("DESC".equals(order)) {
      direction = Direction.DESC;
    } else {
      throw new InvalidRequestException("Illegal Order parameter");
    }

    // get orderBy parameter
    String orderBy = pageParams.getOrderBy();

    PageRequest pageRequest = new PageRequest(index, nbElements, direction, orderBy);

    // get serach parameter
    String search = pageParams.getSearch();

    if ("".equals(search)) {
      search = null;
    }

    QueryParams queryParams = new QueryParams(pageRequest, search);

    return queryParams;
  }

}
