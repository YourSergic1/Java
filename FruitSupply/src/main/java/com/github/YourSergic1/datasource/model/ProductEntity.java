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
@Table(name = "product")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductEntity {
    @Id
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String type;
    @Column(nullable = false)
    Float price;
    UUID supplierId;
}
