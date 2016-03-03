package com.gnostrenoff.cdb.controller.mapper;

import com.gnostrenoff.cdb.dao.util.Order;
import com.gnostrenoff.cdb.dao.util.OrderBy;
import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.impl.CompanyServiceImpl;
import com.gnostrenoff.cdb.spring.ApplicationContextProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class RequestMapper.
 */
public class RequestMapper {

  /**
   * Map method creates a QueryParams from a http request.
   *
   * @param request
   *          the request
   * @return the query params
   */
  public static QueryParams toParams(HttpServletRequest request) {

    // create params object with default values
    QueryParams params = new QueryParams(10, 0, OrderBy.NAME, Order.ASC);
    params.setIndex(1);

    // get attributes
    String StrNbElementPerPage = request.getParameter("nbElementPerPage");
    String strPageIndex = request.getParameter("pageIndex");
    String search = request.getParameter("search");
    String orderBy = request.getParameter("orderBy");
    String order = request.getParameter("order");

    // set parameters if present, leave empty or default value otherwise
    if (search != null && !search.isEmpty()) {
      params.setSearch(search);
    }
    if (orderBy != null && !orderBy.isEmpty()) {
      params.setOrderBy(orderBy);
    }
    if (order != null && !order.isEmpty()) {
      params.setOrder(order);
    }

    if (StrNbElementPerPage != null && strPageIndex != null) {
      int nbElementPerPage = Integer.parseInt(StrNbElementPerPage);
      int pageIndex = Integer.parseInt(strPageIndex);
      params.setIndex(pageIndex);
      params.setNbElements(nbElementPerPage);
      params.setOffset((pageIndex - 1) * nbElementPerPage);
    }

    return params;
  }

  /**
   * Map to computer dto.
   *
   * @param request
   *          the request
   * @return the computer dto
   */
  public static ComputerDto toComputerDto(HttpServletRequest request) {

    CompanyServiceImpl companyService = ApplicationContextProvider.getApplicationContext()
        .getBean("companyService", CompanyServiceImpl.class);

    // get parameters
    String id = request.getParameter("id");
    String name = request.getParameter("name");
    String introduced = request.getParameter("introduced");
    String discontinued = request.getParameter("discontinued");
    String strCompanyId = request.getParameter("companyId");

    Long companyId = null;
    String companyName = null;

    if (strCompanyId == null || strCompanyId.equals("0")) {
      companyId = (long) 0;
    } else {
      companyId = Long.parseLong(request.getParameter("companyId"));
      companyName = companyService.get(companyId).getName();
    }

    // create dto accordingly
    ComputerDto dto = new ComputerDto(name, introduced, discontinued, companyName, companyId);

    if (id != null && !id.isEmpty()) {
      dto.setId(Long.parseLong(id));
    }

    return dto;
  }

}
