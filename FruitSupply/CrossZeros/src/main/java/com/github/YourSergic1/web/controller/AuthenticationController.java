package com.github.YourSergic1.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {
    @GetMapping("/authentication")
    public String authentication(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("loginError", false);
        if (error != null) {
            model.addAttribute("loginError", true);
        }
        return "authentication";
    }
}
