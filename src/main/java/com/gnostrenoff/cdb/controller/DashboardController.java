package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.controller.mapper.RequestMapper;
import com.gnostrenoff.cdb.controller.util.PageCreator;
import com.gnostrenoff.cdb.dto.PageDto;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.model.QueryParams;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

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
   * Do get : get list of computers.
   *
   * @param model
   *          the model
   * @param request
   *          the request
   * @return the string
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @RequestMapping(method = RequestMethod.GET)
  public String displayComputerUpdateForm(ModelMap model, HttpServletRequest request) {

    // create a QueryParams from the request
    QueryParams params = RequestMapper.toParams(request);
    // get computers list
    List<Computer> list = computerService.getList(params);
    // retrieve nb of computers
    int nbTotalComputers = computerService.count(params.getSearch());
    // create page
    PageDto page = PageCreator.create(list, params, nbTotalComputers);

    // set attributes
    model.addAttribute("nbTotalComputers", nbTotalComputers);
    model.addAttribute("page", page);

    return "dashboard";
  }
  
  /**
   * Delete computer.
   *
   * @param selection
   *          the selection
   * @return the string
   */
  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String deleteComputer(@RequestParam("selection") String selection) {

    String[] idTable = selection.split(",");

    if (idTable != null && idTable.length > 0) {
      for (String id : idTable) {
        computerService.delete(Long.parseLong(id));
      }
    }
    return "redirect:/dashboard";
  }
}
