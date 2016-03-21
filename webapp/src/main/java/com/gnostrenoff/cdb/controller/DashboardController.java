package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.controller.mapper.PageQueryMapper;
import com.gnostrenoff.cdb.dao.util.Order;
import com.gnostrenoff.cdb.dao.util.OrderBy;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.dto.PageParams;
import com.gnostrenoff.cdb.dto.QueryParams;
import com.gnostrenoff.cdb.dto.mapper.PageDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * implementation for computer controller. It handles Create, Read, Update and delete computers.
 */
@Controller
public class DashboardController {

  /**
   * Gets the default parameters to use in case there are not present in request.
   *
   * @return the some bean
   */
  @ModelAttribute("params")
  PageParams getDefaultParams() {
    return new PageParams(1, 10, 0, OrderBy.NAME, Order.ASC);
  }

  @Autowired
  private ComputerService computerService;

  @Autowired
  private PageDtoMapper pageDtoMapper;

  /**
   * Do get : get list of computers.
   *
   * @param params the params
   * @param model          the model
   * @return the string
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(value = {"/dashboard", "/"}, method = RequestMethod.GET)
  public String displayComputers(@ModelAttribute("params") PageParams params, Model model) {

    // get computers list
    QueryParams queryParams = PageQueryMapper.toQueryParams(params);
    Page<Computer> page = computerService.getList(queryParams.getPageRequest(), queryParams.getSearchParam());
    // set atributes
    PageDto pageDto = pageDtoMapper.toPageDto(page, params);
    model.addAttribute("page", pageDto);

    return "dashboard";
  }
}
