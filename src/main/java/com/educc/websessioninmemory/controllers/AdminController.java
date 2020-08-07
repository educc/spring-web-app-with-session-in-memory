package com.educc.websessioninmemory.controllers;

import java.security.Principal;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {

  @Secured("ADMIN")
  @GetMapping("/dashboard")
  public ModelAndView index(Principal principal) {
    return new ModelAndView("admin", "data", principal.getName());
  }
}
