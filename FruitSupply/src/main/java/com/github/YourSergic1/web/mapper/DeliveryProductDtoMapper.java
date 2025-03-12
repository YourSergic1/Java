package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.web.model.DeliveryProductDto;

public class DeliveryProductDtoMapper {
    public static DeliveryProductDto deliveryProductToDeliveryProductDto(DeliveryProduct deliveryProduct) {
        DeliveryProductDto deliveryProductDto = new DeliveryProductDto();
        deliveryProductDto.setId(deliveryProduct.getId());
        deliveryProductDto.setProduct(deliveryProduct.getProduct());
        deliveryProductDto.setPrice(deliveryProduct.getPrice());
        deliveryProductDto.setWeight(deliveryProduct.getWeight());
        return deliveryProductDto;
    }
}
