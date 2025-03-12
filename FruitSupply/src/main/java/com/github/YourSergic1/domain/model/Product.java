package com.github.YourSergic1.domain.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    UUID id;
    String name;
    String type;
    Float price;
    UUID supplierId;
}
