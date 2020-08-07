package com.educc.websessioninmemory.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {


  @GetMapping(value = {"/", "/index"})
  public ModelAndView index(HttpSession session) {
    Object attribute = session.getAttribute("name");
    String username = null;
    if (attribute != null && "".getClass().equals(attribute.getClass())) {
      username = (String) attribute;
    }
    return new ModelAndView("home", "data", username);
  }

}

