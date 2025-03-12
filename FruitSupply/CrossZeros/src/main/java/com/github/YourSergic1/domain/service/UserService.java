package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.domain.model.User;

import java.util.UUID;

public interface UserService {
    User createUser(SignUpRequest signUpRequest);

    boolean registerUser(SignUpRequest signUpRequest);

    void deleteUser(UUID id);

    UUID authorizeUser(String authorizationHeader);
}
