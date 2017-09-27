package org.maeno.example.controller;

import org.maeno.example.security.dto.LoginUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping("/")
    public String index() {
        return "login";
    }

    @RequestMapping(value="/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout) {

        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        if (logout != null) {
            modelAndView.addObject("message", "Logged out successfully.");
        }

        return modelAndView;
    }

    @RequestMapping("/main")
    public String main(@AuthenticationPrincipal LoginUser loginUser) {
        return "main";
    }

}
