package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.impl.ComputerServiceImpl;
import com.gnostrenoff.cdb.spring.ApplicationContextProvider;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class DeleteController.
 */
@WebServlet("/delete")
public class DeleteController extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new delete controller.
   *
   * @see HttpServlet#HttpServlet()
   */
  public DeleteController() {
    super();
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

    ComputerService computerService = ApplicationContextProvider.getApplicationContext()
        .getBean("computerService", ComputerServiceImpl.class);
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
