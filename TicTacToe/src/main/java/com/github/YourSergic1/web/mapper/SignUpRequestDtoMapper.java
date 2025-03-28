package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.SignUpRequest;
import com.github.YourSergic1.web.model.SignUpRequestDto;

public class SignUpRequestDtoMapper {
    public static SignUpRequestDto toDto(SignUpRequest signUpRequest) {
        return new SignUpRequestDto(signUpRequest.getLogin(), signUpRequest.getPassword());
    }

    public static SignUpRequest toSignUpRequest(SignUpRequestDto signUpRequestDto) {
        return new SignUpRequest(signUpRequestDto.getLogin(), signUpRequestDto.getPassword());
    }
}
