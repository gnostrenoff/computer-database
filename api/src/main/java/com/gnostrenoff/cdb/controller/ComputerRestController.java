package com.gnostrenoff.cdb.controller;

import com.gnostrenoff.cdb.model.Computer;
import com.gnostrenoff.cdb.service.ComputerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ComputerRestController {
  
  @Autowired
  private ComputerService computerService;
  
  @RequestMapping(value = "/computer/", method = RequestMethod.GET)
  public ResponseEntity<List<Computer>> get() {
    return null;
  }
  
  @RequestMapping(value = "/computer/{id}", method = RequestMethod.GET)
  public ResponseEntity<Computer> get(@PathVariable long id) {
    return new ResponseEntity<Computer>(computerService.get(id), HttpStatus.OK);
  }
  
  @RequestMapping(value = "/computer/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Computer> delete(@PathVariable long id) {
    computerService.delete(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @RequestMapping(value = "/computer", method = RequestMethod.POST)
  public ResponseEntity<Computer> create(@RequestBody Computer computer) {
    computerService.create(computer);
    return new ResponseEntity<>(HttpStatus.OK);
  }
  
  @RequestMapping(value = "/computer/{id}", method = RequestMethod.PUT)
  public ResponseEntity<Computer> update(@PathVariable long id, @RequestBody Computer computer) {
    computerService.update(computer);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
