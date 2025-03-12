package com.github.YourSergic1.datasource.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_entity")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {
    @Id
    UUID id;
    @Column(unique = true, nullable = false)
    String login;
    @Column(nullable = false)
    String password;
}
