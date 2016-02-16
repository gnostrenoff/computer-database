package com.gnostrenoff.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Company;
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		companyService = CompanyServiceImpl.getInstance();
		computerService = ComputerServiceImpl.getInstance();
		
		String name = request.getParameter("name");
		String introduced = request.getParameter("introduced");
		String discontinued = request.getParameter("discontinued");
		long companyId = Long.parseLong(request.getParameter("companyId"));
		Company company = companyService.getCompany(companyId);
		
		String companyName = null;
		if(company != null){
			companyName =company.getName();
		}
		ComputerDto dto = new ComputerDto(name, introduced, discontinued, companyName, companyId);
		Computer computer = ComputerDtoMapper.toComputer(dto);
		computerService.createComputer(computer);
		response.sendRedirect("/computer-database/dashboard");
	}

}
