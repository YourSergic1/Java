package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.datasource.model.DeliveryProductEntity;
import com.github.YourSergic1.datasource.model.ProductEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Scope("singleton")
public interface DeliveryProductRepository extends JpaRepository<DeliveryProductEntity, UUID> {
    List<DeliveryProductEntity> findAllByDeliveryAndProductEntity(DeliveryEntity delivery, ProductEntity productEntity);

    List<DeliveryProductEntity> findAllByDelivery(DeliveryEntity delivery);
}