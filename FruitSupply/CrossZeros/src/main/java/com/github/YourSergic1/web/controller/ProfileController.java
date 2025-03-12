package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.service.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.UUID;

@Controller
public class ProfileController {
    private final RepositoryServiceImpl repositoryService;
    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    ProfileController(RepositoryServiceImpl repositoryService, UserServiceImplementation userServiceImplementation) {
        this.repositoryService = repositoryService;
        this.userServiceImplementation = userServiceImplementation;
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }

    @PostMapping("/delete")
    public String delete(Principal principal) {
        UUID id = repositoryService.getUser(principal.getName()).get().getId();
        userServiceImplementation.deleteUser(id);
        return "redirect:/logout";
    }
}
