package com.github.YourSergic1.datasource.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "supplier")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierEntity {
    @Id
    UUID id;
    @Column(unique = true, nullable = false)
    String name;
    String address;
    String phone;
}
