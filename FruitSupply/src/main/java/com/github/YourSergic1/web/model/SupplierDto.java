package com.github.YourSergic1.web.model;

import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierDto {
    @Id
    UUID id;
    String name;
    String address;
    String phone;
}