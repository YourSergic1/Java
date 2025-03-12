package com.github.YourSergic1.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    UUID id;
    String login;
    String password;

    public User(String login, String password) {
        this.id = UUID.randomUUID();
        this.login = login;
        this.password = password;
    }

    public User(UUID id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
    }
}
