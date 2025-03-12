package com.github.YourSergic1.web.model;

import com.github.YourSergic1.domain.model.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryProductDto {
    UUID id;
    Product product;
    float price;
    float weight;
}