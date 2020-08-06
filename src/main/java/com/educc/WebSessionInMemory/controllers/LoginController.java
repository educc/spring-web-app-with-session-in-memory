package com.educc.WebSessionInMemory.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/")
public class LoginController {

  @PostMapping("/login")
  public RedirectView makeLogin(ModelMap model) {
    //TODO: create session cookie here
    return new RedirectView("/");
  }
}
