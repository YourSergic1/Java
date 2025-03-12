package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationServiceImplementation implements AuthorizationService {
    private final UserServiceImplementation userServiceImplementation;

    @Autowired
    AuthorizationServiceImplementation(UserServiceImplementation userServiceImplementation) {
        this.userServiceImplementation = userServiceImplementation;
    }

    @Override
    public boolean registerUser(SignUpRequest signUpRequest) {
        return userServiceImplementation.registerUser(signUpRequest);
    }

    @Override
    public UUID authorizeUser(String authorizationHeader) {
        return userServiceImplementation.authorizeUser(authorizationHeader);
    }
}
