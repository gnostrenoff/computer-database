package com.gnostrenoff.cdb.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.controllers.utils.PageCreator;
import com.gnostrenoff.cdb.dao.utils.OrderBy;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.services.ComputerService;
import com.gnostrenoff.cdb.services.impl.ComputerServiceImpl;

/**
 * Servlet implementation class ComputerServlet
 */
@WebServlet("/dashboard")
public class DashboardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ComputerService computerService;

	/**
	 * Default constructor.
	 */
	public DashboardController() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		computerService = ComputerServiceImpl.getInstance();
		List<Computer> list = null;

		// create a default page
		QueryParams queryParams = new QueryParams(1, 10);
		queryParams.setOffset(0);
		queryParams.setOrderBy(OrderBy.NAME);
		queryParams.setOrder("ASC");

		// get attributes
		String StrNbElementPerPage = request.getParameter("nbElementPerPage");
		String strPageIndex = request.getParameter("pageIndex");
		String search = request.getParameter("search");
		String orderBy = request.getParameter("orderBy");
		String order = request.getParameter("order");

		// set search parameter if present
		if (search != null && !search.isEmpty()) {
			queryParams.setSearch(search);
		}
		if (orderBy != null && !orderBy.isEmpty()) {
			queryParams.setOrderBy(orderBy);
		}
		if (order != null && !order.isEmpty()) {
			queryParams.setOrder(order);
		}

		// retrieve nb of computers
		int nbTotalComputers = computerService.count(queryParams);

		// get computers depending on parameters if present, get default page
		// otherwise
		if (StrNbElementPerPage != null && strPageIndex != null) {
			int nbElementPerPage = Integer.parseInt(StrNbElementPerPage);
			int pageIndex = Integer.parseInt(strPageIndex);
			queryParams.setIndex(pageIndex);
			queryParams.setNbElements(nbElementPerPage);
			queryParams.setOffset((pageIndex - 1) * nbElementPerPage);
			list = computerService.getList(queryParams);
		} else // default page
			list = computerService.getList(queryParams);

		PageDto page = PageCreator.create(list, queryParams, nbTotalComputers);

		// set attributes
		request.setAttribute("nbTotalComputers", nbTotalComputers);
		request.setAttribute("page", page);

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
