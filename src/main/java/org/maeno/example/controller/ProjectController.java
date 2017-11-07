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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProjectController {

    @Autowired
    HttpSession session;

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

    @RequestMapping(value = "/edit", params = "deleteCheck", method = RequestMethod.POST)
    public ModelAndView edit(@ModelAttribute("form") ProjectForm form) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("delete");
        session.setAttribute("target", form);

        final List<Project> projectList = projectService.selectProject(form.getProjectList());
        modelAndView.addObject("deleteProjectList", projectList);

        return modelAndView;
    }

    @RequestMapping(value = "/delete", params = "delete")
    public ModelAndView delete(@AuthenticationPrincipal LoginUser loginUser) {
        ProjectForm projectForm = (ProjectForm) session.getAttribute("target");

        final boolean isSuccess = projectService.deleteProject(projectForm.getProjectList());

        if (isSuccess) {
            return main(loginUser);
        }
        return null;
    }

    @RequestMapping(value="/delete", params = "cancel")
    public ModelAndView cancel() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");

        ProjectForm projectForm = (ProjectForm) session.getAttribute("target");
        final List<Project> projectList = projectService.selectProject(projectForm.getProjectList());

        modelAndView.addObject("projectList", projectList);
        return modelAndView;
    }
}
