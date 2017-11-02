package org.maeno.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProjectController {

    @RequestMapping(value = "/edit")
    public ModelAndView edit() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("delete");
        return modelAndView;
    }
}
