package com.github.YourSergic1.datasource.repository;

import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.datasource.model.SupplierEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Scope("singleton")
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Long> {
    DeliveryEntity getDeliveryEntityById(UUID id);

    List<DeliveryEntity> getAllBySupplierAndDeliveryDateAfterAndDeliveryDateBefore(SupplierEntity supplierEntity, LocalDate start, LocalDate end);
}

