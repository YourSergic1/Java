package com.github.YourSergic1.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    UUID id;
    String login;
    String password;
    Set<Role> roles = new HashSet<>();

    public User(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
        this.roles.add(Role.USER);
    }

    public User(UUID id, String login, String password, Set<Role> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }
}
