package com.github.YourSergic1.domain.model.jwt;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    String login;
    String password;
}

