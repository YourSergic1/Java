package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.service.AuthorizationServiceImplementation;
import com.github.YourSergic1.web.mapper.SignUpRequestDtoMapper;
import com.github.YourSergic1.web.model.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {
    private final AuthorizationServiceImplementation authorizationServiceImplementation;

    @Autowired
    RegistrationController(AuthorizationServiceImplementation authorizationServiceImplementation) {
        this.authorizationServiceImplementation = authorizationServiceImplementation;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new SignUpRequestDto());
        model.addAttribute("registrationSuccess", false);
        model.addAttribute("registrationError", false);
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute("user") SignUpRequestDto signUpRequestDto, Model model) {
        SignUpRequest signUpRequest = SignUpRequestDtoMapper.toSignUpRequest(signUpRequestDto);
        boolean isCreated = authorizationServiceImplementation.registerUser(signUpRequest);
        if (isCreated) {
            model.addAttribute("registrationSuccess", true);
            model.addAttribute("registrationError", false);
        } else {
            model.addAttribute("registrationSuccess", false);
            model.addAttribute("registrationError", true);
        }
        return "registration";
    }
}
