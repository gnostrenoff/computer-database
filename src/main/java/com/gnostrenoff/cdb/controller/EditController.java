package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.dto.util.ComputerDtoValidator;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServlet;
import javax.validation.Valid;

/**
 * Servlet implementation class EditController.
 */
@Controller
@RequestMapping("/edit")
public class EditController extends HttpServlet {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The computer service. */
  @Autowired
  private ComputerService computerService;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Instantiates a new edits the controller.
   *
   * @see HttpServlet#HttpServlet()
   */
  public EditController() {
  }

  /**
   * Display computer form for an update.
   *
   * @param id
   *          the id
   * @param model
   *          the model
   * @return the string
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public String displayComputerUpdateForm(@PathVariable("id") long id, ModelMap model) {

    // retrieve computer
    Computer computer = computerService.get(id);
    ComputerDto computerDto = ComputerDtoMapper.toDto(computer);
    ComputerDtoValidator.validate(computerDto);

    model.addAttribute("companies", companyService.getList());
    model.addAttribute("computerDto", computerDto);
    return "editComputer";

  }

  /**
   * Adds the computer.
   *
   * @param computerDto
   *          the computer dto
   * @param result
   *          the result
   * @param model
   *          the model
   * @return the string
   */
  @RequestMapping(method = RequestMethod.POST)
  public String updateComputer(@Valid ComputerDto computerDto, BindingResult result,
      ModelMap model) {

    if (result.hasErrors()) {
      model.addAttribute("companies", companyService.getList());
      return "addComputer";
    } else {
      // get computer from dto
      Computer computer = ComputerDtoMapper.toComputer(computerDto);

      // then save computer into database
      computerService.update(computer);
      return "redirect:/dashboard";
    }

  }

}
