package com.github.YourSergic1.datasource.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "delivery")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeliveryEntity {
    @Id
    UUID id;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    SupplierEntity supplier;
    @Column(nullable = false)
    LocalDate deliveryDate;
}
