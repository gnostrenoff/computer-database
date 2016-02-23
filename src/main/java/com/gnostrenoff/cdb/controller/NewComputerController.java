package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.controller.mapper.RequestMapper;
import com.gnostrenoff.cdb.controller.util.ComputerDtoValidator;
import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.ComputerService;
import com.gnostrenoff.cdb.service.impl.CompanyServiceImpl;
import com.gnostrenoff.cdb.service.impl.ComputerServiceImpl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class newComputerServlet.
 */
@WebServlet("/new")
public class NewComputerController extends HttpServlet {
  
  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The computer service. */
  private ComputerService computerService;
  
  /** The company service. */
  private CompanyService companyService;

  /**
   * Instantiates a new new computer controller.
   *
   * @see HttpServlet#HttpServlet()
   */
  public NewComputerController() {
    super();
  }

  /**
   * Do get.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    companyService = CompanyServiceImpl.getInstance();

    request.setAttribute("companies", companyService.getList());
    request.getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);
  }

  /**
   * Do post.
   *
   * @param request the request
   * @param response the response
   * @throws ServletException the servlet exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    companyService = CompanyServiceImpl.getInstance();
    computerService = ComputerServiceImpl.getInstance();

    // create computer dto from request
    ComputerDto dto = RequestMapper.toComputerDto(request);
    
    //validation
    ComputerDtoValidator.validate(dto);
    
    //get computer from dto
    Computer computer = ComputerDtoMapper.toComputer(dto);

    // then save computer into database
    computerService.create(computer);

    response.sendRedirect("/computer-database/dashboard");
  }

}
