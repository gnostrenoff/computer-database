package com.gnostrenoff.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.CompanyService;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.CompanyServiceImpl;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

/**
 * Servlet implementation class newComputerServlet
 */
@WebServlet("/newComputer")
public class newComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;
	private CompanyService companyService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public newComputerServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		companyService = CompanyServiceImpl.getInstance();

		request.setAttribute("companies", companyService.getCompanies());
		request.getRequestDispatcher("/WEB-INF/jsp/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		companyService = CompanyServiceImpl.getInstance();
		computerService = ComputerServiceImpl.getInstance();

		//get parameters
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		String strCompanyId = request.getParameter("companyId");
		
		Long companyId = null;
		String companyName = null;
		
		if(strCompanyId == null || strCompanyId.equals("0")){
			companyId = (long) 0;		
		}else{
			companyId = Long.parseLong(request.getParameter("companyId"));
			companyName = companyService.getCompany(companyId).getName();
		}
		
		//create dto accordingly
		ComputerDto dto = new ComputerDto(name, introduced, discontinued, companyName, companyId);
		Computer computer = ComputerDtoMapper.toComputer(dto);
		
		//then save computer into database
		computerService.createComputer(computer);

		response.sendRedirect("/computer-database/dashboard");
	}

}