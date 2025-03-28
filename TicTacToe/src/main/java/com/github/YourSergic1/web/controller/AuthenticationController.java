package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.domain.model.jwt.JwtRequest;
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
public class AuthenticationController {
    AuthorizationService authorizationService;

    @Autowired
    public AuthenticationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping("/auth")
    public String authentication() {
        return "auth";
    }

    @PostMapping("/auth")
    public ResponseEntity<JwtResponse> auth(@RequestBody JwtRequest request) {
        final JwtResponse token;
        try {
            token = authorizationService.authorization(request);
        } catch (AuthException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new JwtResponse(null, null));
        }
        return ResponseEntity.ok(token);
    }
}
