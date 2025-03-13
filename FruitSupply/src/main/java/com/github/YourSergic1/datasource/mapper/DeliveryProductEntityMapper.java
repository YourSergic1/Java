package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.DeliveryProductEntity;
import com.github.YourSergic1.domain.model.DeliveryProduct;

public class DeliveryProductEntityMapper {
    public static DeliveryProductEntity modelToEntity(DeliveryProduct deliveryProduct) {
        DeliveryProductEntity deliveryProductEntity = new DeliveryProductEntity();
        deliveryProductEntity.setId(deliveryProduct.getId());
        deliveryProductEntity.setProductEntity(ProductEntityMapper.ModelToEntity(deliveryProduct.getProduct()));
        deliveryProductEntity.setDelivery(DeliveryEntityMapper.ModelToEntity(deliveryProduct.getDelivery()));
        deliveryProductEntity.setPrice(deliveryProduct.getPrice());
        deliveryProductEntity.setWeight(deliveryProduct.getWeight());
        return deliveryProductEntity;
    }

    public static DeliveryProduct EntityToModel(DeliveryProductEntity deliveryProductEntity) {
        DeliveryProduct deliveryProduct = new DeliveryProduct();
        deliveryProduct.setId(deliveryProductEntity.getId());
        deliveryProduct.setProduct(ProductEntityMapper.EntityToModel(deliveryProductEntity.getProductEntity()));
        deliveryProduct.setDelivery(DeliveryEntityMapper.EntityToModel(deliveryProductEntity.getDelivery()));
        deliveryProduct.setPrice(deliveryProductEntity.getPrice());
        deliveryProduct.setWeight(deliveryProductEntity.getWeight());
        return deliveryProduct;
    }
}
