package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.model.jwt.JwtResponse;
import com.github.YourSergic1.domain.service.AuthorizationService;
import jakarta.security.auth.message.AuthException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
    AuthorizationService authorizationService;

    @Autowired
    public RegistrationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<JwtResponse> registrationPost(@RequestBody SignUpRequest request) {
        final JwtResponse token;
        try {
            token = authorizationService.registration(request);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JwtResponse(null, null));
        }
        return ResponseEntity.ok(token);
    }
}
