package com.gnostrenoff.cdb.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * The Class ErrorHandlerController.
 */
@ControllerAdvice
public class ErrorHandlerController {
  /**
   * Exception.
   *
   * @param exception the exception
   * @return the string
   */
  @ExceptionHandler(Exception.class)
  public ModelAndView exception(Exception exception) {

    ModelAndView model = new ModelAndView();
    model.addObject("exception", exception);
    model.setViewName("500");

    return model;
  }

}
