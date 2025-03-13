package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, UUID> {
    List<ProductEntity> findBySupplierId(UUID supplierId);
}
