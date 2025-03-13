package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface RepositoryService {
    void addSupplier(Supplier supplier);

    void addProduct(Product product);

    void addDelivery(Delivery delivery);

    void addDeliveryProduct(DeliveryProduct deliveryProduct);

    void deleteDeliveryProduct(UUID id);

    List<Supplier> getAllSupplies();

    Supplier getSupplier(String name);

    Supplier getSupplier(UUID id);

    List<Product> findProductsBySupplierId(UUID supplierId);

    Delivery getDeliveryById(UUID id);

    Product getProductById(UUID id);

    List<DeliveryProduct> findDeliveryProductsByDeliveryAndProduct(Delivery delivery, Product product);

    List<Delivery> deliveriesBetweenDatesAndBySupply(Supplier supplier, LocalDate from, LocalDate to);

    List<DeliveryProduct> findDeliveryProductsByDelivery(Delivery delivery);
}
