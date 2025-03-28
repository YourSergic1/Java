package com.github.YourSergic1.web.model;

import com.github.YourSergic1.domain.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    UUID id;
    String login;
    String password;
    Set<Role> roles = new HashSet<>();
}
