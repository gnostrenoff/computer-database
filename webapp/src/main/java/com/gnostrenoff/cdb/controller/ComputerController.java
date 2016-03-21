package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.dao.util.Order;
import com.gnostrenoff.cdb.dao.util.OrderBy;
import com.gnostrenoff.cdb.dto.ComputerDto;
import com.gnostrenoff.cdb.dto.PageParams;
import com.gnostrenoff.cdb.dto.mapper.ComputerDtoMapper;
import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.CompanyService;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * implementation for computer controller. It handles Create, Read, Update and delete computers.
 */
@Controller
@RequestMapping("/computer")
public class ComputerController {

  /**
   * Gets the default parameters to use in case there are not present in request.
   *
   * @return the some bean
   */
  @ModelAttribute("params")
  PageParams getDefaultParams() {
    return new PageParams(1, 10, 0, OrderBy.NAME, Order.ASC);
  }

  @Autowired
  private ComputerService computerService;

  /** The company service. */
  @Autowired
  private CompanyService companyService;

  @Autowired
  private ComputerDtoMapper computerDtoMapper;

  /**
   * Display computer form for a creation.
   *
   * @param model
   *          the model
   * @return the string
   */
  @RequestMapping(value = "/admin/new", method = RequestMethod.GET)
  public String displayComputerCreationForm(Model model) {

    model.addAttribute("companies", companyService.getList());
    model.addAttribute("computerDto", new ComputerDto());
    model.addAttribute("action", "new");
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
  @RequestMapping(value = "/admin/new", method = RequestMethod.POST)
  public String addComputer(@Valid ComputerDto computerDto, BindingResult result, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("companies", companyService.getList());
      model.addAttribute("action", "new");
      return "editComputer";
    } else {
      // get computer from dto
      Computer computer = computerDtoMapper.toComputer(computerDto);
      // then save computer into database
      computerService.create(computer);
      return "redirect:/dashboard";
    }
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
  @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
  public String displayComputerUpdateForm(@PathVariable("id") long id, Model model) {

    // retrieve computer
    Computer computer = computerService.get(id);
    ComputerDto computerDto = computerDtoMapper.toDto(computer);

    model.addAttribute("companies", companyService.getList());
    model.addAttribute("computerDto", computerDto);
    model.addAttribute("action", "edit");
    return "editComputer";

  }

  /**
   * Updates the computer the computer.
   *
   * @param computerDto
   *          the computer dto
   * @param result
   *          the result
   * @param model
   *          the model
   * @return the string
   */
  @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
  public String updateComputer(@Valid ComputerDto computerDto, BindingResult result, Model model) {

    if (result.hasErrors()) {
      model.addAttribute("companies", companyService.getList());
      model.addAttribute("action", "edit");
      return "editComputer";
    } else {
      // get computer from dto
      Computer computer = computerDtoMapper.toComputer(computerDto);

      // then save computer into database
      computerService.update(computer);
      return "redirect:/dashboard";
    }
  }

  /**
   * Delete computer.
   *
   * @param selection
   *          the selection
   * @return the string
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
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
