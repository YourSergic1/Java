package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.datasource.model.DeliveryProductEntity;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;

import java.util.ArrayList;
import java.util.List;

public class DeliveryEntityMapper {

    public static DeliveryEntity ModelToEntity(Delivery delivery) {
        DeliveryEntity deliveryEntity = new DeliveryEntity();
        deliveryEntity.setId(delivery.getId());
        deliveryEntity.setSupplier(SupplierEntityMapper.ModelToEntity(delivery.getSupplier()));
        deliveryEntity.setDeliveryDate(delivery.getDeliveryDate());
        List<DeliveryProduct> deliveryProductsList = delivery.getProducts();
        List<DeliveryProductEntity> deliveryProductEntityList=new ArrayList<>();
        deliveryProductsList.forEach(deliveryProduct -> deliveryProductEntityList.add(DeliveryProductEntityMapper.modelToEntity(deliveryProduct)));
        deliveryEntity.setProducts(deliveryProductEntityList);
        return deliveryEntity;
    }

    public static Delivery EntityToModel(DeliveryEntity deliveryEntity) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryEntity.getId());
        delivery.setDeliveryDate(deliveryEntity.getDeliveryDate());
        delivery.setSupplier(SupplierEntityMapper.EntityToModel(deliveryEntity.getSupplier()));
        List<DeliveryProduct> deliveryProductsList = new ArrayList<>();
        List<DeliveryProductEntity> deliveryProductEntityList=deliveryEntity.getProducts();
        deliveryProductEntityList.forEach(deliveryProductEntity -> deliveryProductsList.add(DeliveryProductEntityMapper.EntityToModel(deliveryProductEntity)));
        delivery.setProducts(deliveryProductsList);
        return delivery;
    }
}
