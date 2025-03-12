package com.github.YourSergic1.domain.model;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    UUID id;
    String name;
    String address;
    String phone;
}
