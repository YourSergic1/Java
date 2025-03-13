package com.github.YourSergic1.domain.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryProduct {
    UUID id;
    Product product;
    Delivery delivery;
    float price;
    float weight;
}
