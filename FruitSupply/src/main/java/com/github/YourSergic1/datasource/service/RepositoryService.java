package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.model.SupplierEntity;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryService {
    boolean addSupplier(Supplier supplier);
    boolean addProduct(Product product);
    boolean addDelivery(Delivery delivery);
    List<Supplier> getAllSupplies();
    Supplier getSupplier(String name);
    List<Product> findProductsBySupplierId(UUID supplierId);
}
