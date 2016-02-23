package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.controller.mapper.RequestMapper;
import com.gnostrenoff.cdb.controller.util.PageCreator;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.impl.ComputerServiceImpl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ComputerServlet.
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The computer service. */
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
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    computerService = ComputerServiceImpl.getInstance();

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
   * Do post.
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
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
