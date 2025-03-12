package com.github.YourSergic1.datasource.model;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestEntity {
    String login;
    String password;
}