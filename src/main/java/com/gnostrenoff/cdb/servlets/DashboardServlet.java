package com.gnostrenoff.cdb.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gnostrenoff.cdb.mappers.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.page.Page;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		computerService = ComputerServiceImpl.getInstance();	
		int totalComputer = computerService.getRowCount();
		Page.updateValues(totalComputer);		
		
		if(request.getParameter("pageIndex") != null)
			Page.currentPageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		if(request.getParameter("nbElementPerPage") != null){
			Page.nbElementPerPage = Integer.parseInt(request.getParameter("nbElementPerPage"));
			Page.currentPageIndex = 1;
		}		
		if("true".equals(request.getParameter("next")) && Page.currentPageIndex < Page.nbPages)
			Page.currentPageIndex++;
		if("true".equals(request.getParameter("prev")) && Page.currentPageIndex > 1)
			Page.currentPageIndex--;
		if("true".equals(request.getParameter("last")))
			Page.currentPageIndex = Page.nbPages;
		
		request.setAttribute("nbTotalComputers", totalComputer);
		request.setAttribute("nbPages", Page.nbPages);
		List<Computer> computerList = computerService.getComputers(Page.nbElementPerPage, Page.computeOffset());
		request.setAttribute("computers", ComputerDtoMapper.toDtoList(computerList));		
		request.getRequestDispatcher("/WEB-INF/jsp/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
