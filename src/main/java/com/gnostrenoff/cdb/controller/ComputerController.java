package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Servlet implementation class newComputerServlet.
 */
@Controller
@RequestMapping("/new")
public class ComputerController {

  /** The computer service. */
  @Autowired
  private ComputerService computerService;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Display computer form for a creation.
   *
   * @param model
   *          the model
   * @return the string
   */
  @RequestMapping(method = RequestMethod.GET)
  public String displayComputerCreationForm(ModelMap model) {

    model.addAttribute("companies", companyService.getList());
    model.addAttribute("computerDto", new ComputerDto());
    return "addComputer";

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
  public String addComputer(@Valid ComputerDto computerDto, BindingResult result, ModelMap model) {

    if (result.hasErrors()) {
      model.addAttribute("companies", companyService.getList());
      return "addComputer";
    } else {
      // get computer from dto
      Computer computer = ComputerDtoMapper.toComputer(computerDto);
      // then save computer into database
      computerService.create(computer);
      return "redirect:/dashboard";
    }

  }

}
