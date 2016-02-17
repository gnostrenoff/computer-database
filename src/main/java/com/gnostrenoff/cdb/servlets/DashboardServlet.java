package com.gnostrenoff.cdb.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.dto.mappers.PageDtoMapper;
import com.gnostrenoff.cdb.model.Page;
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

		// create a default page
		Page page = new Page(1, 10);

		// get attributes
		String StrNbElementPerPage = request.getParameter("nbElementPerPage");
		String strPageIndxex = request.getParameter("pageIndex");

		// get computers depending on parameters if present, get default page
		// otherwise
		if (StrNbElementPerPage != null && strPageIndxex != null) {
			page.setNbElements(Integer.parseInt(StrNbElementPerPage));
			page.setIndex(Integer.parseInt(strPageIndxex));
			computerService.fillPage(page);
		} else // default page
			computerService.fillPage(page);

		PageDto pageDto = PageDtoMapper.toDto(page);

		// set attributes
		request.setAttribute("nbTotalComputers", computerService.count());
		request.setAttribute("page", pageDto);

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
