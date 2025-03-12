package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.SignUpRequest;

import java.util.UUID;

public interface AuthorizationService {
    boolean registerUser(SignUpRequest signUpRequest);

    UUID authorizeUser(String authorizationHeader);
}
