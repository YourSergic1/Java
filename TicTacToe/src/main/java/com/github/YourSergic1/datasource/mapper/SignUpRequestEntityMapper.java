package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.SignUpRequestEntity;
import com.github.YourSergic1.domain.model.SignUpRequest;

public class SignUpRequestEntityMapper {
    public static SignUpRequestEntity SingUpRequestToEntity(SignUpRequest signUpRequest) {
        return new SignUpRequestEntity(signUpRequest.getLogin(), signUpRequest.getPassword());
    }

    public static SignUpRequest EntityToSignUpRequest(SignUpRequestEntity signUpRequestEntity) {
        return new SignUpRequest(signUpRequestEntity.getLogin(), signUpRequestEntity.getPassword());
    }
}
