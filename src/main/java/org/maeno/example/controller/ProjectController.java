package org.maeno.example.controller;

import org.maeno.example.domain.Project;
import org.maeno.example.form.ProjectForm;
import org.maeno.example.security.dto.LoginUser;
import org.maeno.example.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping("/main")
    public ModelAndView main(@AuthenticationPrincipal LoginUser loginUser) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        final List<Project> projectList = projectService.list();
        modelAndView.addObject("projectList", projectList);

        return modelAndView;
    }

    @RequestMapping(value = "/edit")
    public ModelAndView edit(@ModelAttribute("form") ProjectForm form) {
        final ModelAndView modelAndView = new ModelAndView();
        // TODO セッションに格納する
        modelAndView.setViewName("delete");

        final List<Project> projectList = projectService.selectProjectByChecked(form.getProjectList());
        modelAndView.addObject("deleteProjectList", projectList);

        return modelAndView;
    }
}
