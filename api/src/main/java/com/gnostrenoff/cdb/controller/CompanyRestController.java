package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.model.Company;
import com.gnostrenoff.cdb.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The Class ComputerRestController.
 */
@RestController
@RequestMapping(value = "/api/company")
public class CompanyRestController {

  /** The computer service. */
  @Autowired
  private CompanyService companyService;

  /**
   * Deletes the computer matching with given id.
   *
   * @param id the id
   * @return the response entity
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  //@PreAuthorize("hasRole('ROLE_ADMIN')")
  public ResponseEntity<Company> delete(@PathVariable long id) {
    companyService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  /**
   * Gets the list of companies.
   *
   * @return the response entity
   */
  @RequestMapping(method = RequestMethod.GET)
  //@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  public ResponseEntity<List<Company>> get() {
    List<Company> companies = companyService.getList();
    return new ResponseEntity<> (companies, HttpStatus.OK);
  }

}
