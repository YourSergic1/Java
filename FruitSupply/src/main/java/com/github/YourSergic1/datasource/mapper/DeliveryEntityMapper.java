package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.domain.model.Delivery;

public class DeliveryEntityMapper {

    public static DeliveryEntity ModelToEntity(Delivery delivery) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setId(delivery.getId());
        deliveryEntity.setSupplier(SupplierEntityMapper.ModelToEntity(delivery.getSupplier()));
        deliveryEntity.setDeliveryDate(delivery.getDeliveryDate());

        return deliveryEntity;
    }

    public static Delivery EntityToModel(DeliveryEntity deliveryEntity) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryEntity.getId());
        delivery.setDeliveryDate(deliveryEntity.getDeliveryDate());
        delivery.setSupplier(SupplierEntityMapper.EntityToModel(deliveryEntity.getSupplier()));
        return delivery;
    }
}
