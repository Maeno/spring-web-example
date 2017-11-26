package org.maeno.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/login")
@Controller
public class AuthController {

    @GetMapping
    public String loginForm() {
        return "login";
    }

    @GetMapping(params = "error")
    public String loginError(@RequestParam(value = "error", required = false)
                             final String error,
                             final Model model) {
        return loginForm();
    }

    @RequestMapping(params = "logout")
    public String logout(final Model model) {
        model.addAttribute("message", "Logged out successfully.");
        return loginForm();
    }

}
