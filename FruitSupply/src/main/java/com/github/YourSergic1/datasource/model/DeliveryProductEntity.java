package com.github.YourSergic1.datasource.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "delivery_product")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryProductEntity {
    @Id
    UUID id;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    ProductEntity productEntity;
    @ManyToOne
    @JoinColumn(name = "delivery_id", nullable = false)  // Добавляем внешний ключ на сущность DeliveryEntity
    DeliveryEntity delivery;
    float price;
    float weight;
}
