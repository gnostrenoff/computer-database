package com.gnostrenoff.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.dto.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ComputerService computerService;

	/**
	 * Default constructor.
	 */
	public DashboardServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		computerService = ComputerServiceImpl.getInstance();

		int pageIndex = 1;
		int nbElementsPerPage = 10;
		List<Computer> computerList;

		//get attributes
		String StrNbElementPerPage = request.getParameter("nbElementPerPage");
		String strOffset = request.getParameter("offset");
		
		//get computers depending on parameters if present, get default amount otherwise
		if (StrNbElementPerPage != null && strOffset != null) {
			nbElementsPerPage = Integer.parseInt(StrNbElementPerPage);
			int offset = Integer.parseInt(strOffset);
			computerList = computerService.getList(nbElementsPerPage, offset);
			pageIndex = offset / nbElementsPerPage + 1;
		} else
			computerList = computerService.getList(10, 0);

		//set attributes
		request.setAttribute("currentPageIndex", pageIndex);
		request.setAttribute("nbTotalComputers", computerService.count());
		request.setAttribute("nbElementsPerPage", nbElementsPerPage);
		request.setAttribute("computers", ComputerDtoMapper.toDtoList(computerList));
		
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
