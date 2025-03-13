package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.web.model.DeliveryDto;

public class DeliveryDtoMapper {

    public static DeliveryDto modelToDto(Delivery delivery) {
        DeliveryDto deliveryDto = new DeliveryDto();
        deliveryDto.setId(delivery.getId());
        deliveryDto.setDeliveryDate(delivery.getDeliveryDate());
        deliveryDto.setSupplier(delivery.getSupplier());
        return deliveryDto;
    }

    public static Delivery dtoToModel(DeliveryDto deliveryDto) {
        Delivery delivery = new Delivery();
        delivery.setId(deliveryDto.getId());
        delivery.setDeliveryDate(deliveryDto.getDeliveryDate());
        delivery.setSupplier(deliveryDto.getSupplier());
        return delivery;
    }
}
