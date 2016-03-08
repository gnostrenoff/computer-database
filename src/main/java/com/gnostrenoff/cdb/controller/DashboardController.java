package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.controller.mapper.RequestMapper;
import com.gnostrenoff.cdb.controller.util.PageCreator;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComputerServlet.
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The computer service. */
  @Autowired
  private ComputerService computerService;

  /**
   * Default constructor.
   */
  public DashboardController() {
  }

  /**
   * Do get.
   *
   * @param request
   *          the request
   * @param response
   *          the response
   * @throws ServletException
   *           the servlet exception
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(method = RequestMethod.GET)
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // create a QueryParams from the request
    QueryParams params = RequestMapper.toParams(request);
    // get computers list
    List<Computer> list = computerService.getList(params);
    // retrieve nb of computers
    int nbTotalComputers = computerService.count(params.getSearch());
    // create page
    PageDto page = PageCreator.create(list, params, nbTotalComputers);

    // set attributes
    request.setAttribute("nbTotalComputers", nbTotalComputers);
    request.setAttribute("page", page);

    request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
  }

  /**
   * Do post : delete a computer
   *
   * @param request
   *          the request
   * @param response
   *          the response
   * @throws ServletException
   *           the servlet exception
   * @throws IOException
   *           Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(method = RequestMethod.POST)
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String selection = request.getParameter("selection");
    String[] idTable = selection.split(",");

    if (idTable != null && idTable.length > 0) {
      for (String id : idTable) {
        computerService.delete(Long.parseLong(id));
      }
    }

    response.sendRedirect("/computer-database/dashboard");

  }

}
