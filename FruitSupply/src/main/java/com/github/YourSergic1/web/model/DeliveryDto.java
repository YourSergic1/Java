package com.github.YourSergic1.web.model;

import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Supplier;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryDto {
    UUID id;
    Supplier supplier;
    LocalDate deliveryDate;
}