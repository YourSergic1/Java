package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.DeliveryProductEntity;
import com.github.YourSergic1.datasource.model.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SupplierEntityRepository extends JpaRepository<SupplierEntity, UUID> {
    List<SupplierEntity> getAllByName(String name);
    Optional<SupplierEntity> findByName(String login);
}
