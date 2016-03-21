package com.gnostrenoff.cdb.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * implementation for computer controller. It handles Create, Read, Update and delete computers.
 */
@Controller
public class AccessController {

  /**
   * Display login form.
   *
   * @return the string
   */
  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String displayLoginForm(Model model) {
    model.addAttribute("message", "Who are you ?");
    return "login";
  }
  
  /**
   * Display login form.
   *
   * @return the string
   */
  @RequestMapping(value = "login/fail", method = RequestMethod.GET)
  public String displayLoginFormAfterFail(Model model) {
    model.addAttribute("message", "Sorry, We were not able to authenticate you, please try again");
    return "login";
  }
  
  /**
   * Performs logout, display page that indicates user has been correctly logged out if success.
   *
   * @param request the request
   * @param response the response
   * @return the string
   */
  @RequestMapping(value="/logout", method = RequestMethod.GET)
  public String logout (HttpServletRequest request, HttpServletResponse response) {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
      }
      return "logout";
  }
  
  /**
   * Display access denied.
   *
   * @param model the model
   * @return the string
   */
  @RequestMapping(value = "/denied", method = RequestMethod.GET)
  public String displayAccessDenied(Model model) {
    model.addAttribute("message", "You are not allowed to perform this action.");
    return "403";
  }
  
}
