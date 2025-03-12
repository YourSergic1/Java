package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.mapper.DeliveryEntityMapper;
import com.github.YourSergic1.datasource.mapper.ProductEntityMapper;
import com.github.YourSergic1.datasource.mapper.SupplierEntityMapper;
import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.datasource.model.ProductEntity;
import com.github.YourSergic1.datasource.model.SupplierEntity;
import com.github.YourSergic1.datasource.repository.DeliveryProductRepository;
import com.github.YourSergic1.datasource.repository.DeliveryRepository;
import com.github.YourSergic1.datasource.repository.ProductEntityRepository;
import com.github.YourSergic1.datasource.repository.SupplierEntityRepository;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RepositoryServiceImpl implements RepositoryService {
    DeliveryProductRepository deliveryProductRepository;
    DeliveryRepository deliveryRepository;
    ProductEntityRepository productEntityRepository;
    SupplierEntityRepository supplierEntityRepository;

    @Autowired
    RepositoryServiceImpl(DeliveryProductRepository deliveryProductRepository,DeliveryRepository deliveryRepository,ProductEntityRepository productEntityRepository,SupplierEntityRepository supplierEntityRepository) {
        this.deliveryProductRepository = deliveryProductRepository;
        this.deliveryRepository = deliveryRepository;
        this.productEntityRepository = productEntityRepository;
        this.supplierEntityRepository = supplierEntityRepository;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = SupplierEntityMapper.ModelToEntity(supplier);
        supplierEntityRepository.save(supplierEntity);
        return false;
    }

    @Override
    public boolean addProduct(Product product) {
        ProductEntity productEntity = ProductEntityMapper.ModelToEntity(product);
        productEntityRepository.save(productEntity);
        return false;
    }

    @Override
    public boolean addDelivery(Delivery delivery) {
        DeliveryEntity deliveryEntity = DeliveryEntityMapper.ModelToEntity(delivery);
        deliveryRepository.save(deliveryEntity);
        return false;
    }

    @Override
    public List<Supplier> getAllSupplies(){
        List<SupplierEntity> supplierEntities = supplierEntityRepository.findAll();
        List<Supplier> suppliers = new ArrayList<>();
        supplierEntities.forEach(supplierEntity -> suppliers.add(SupplierEntityMapper.EntityToModel(supplierEntity)));
        return suppliers;
    }

    @Override
    public Supplier getSupplier(String name) {
        Optional<SupplierEntity> supplierEntity=supplierEntityRepository.findByName(name);
        return supplierEntity.map(SupplierEntityMapper::EntityToModel).orElse(null);
    }

    @Override
    public List<Product> findProductsBySupplierId(UUID supplierId) {
        List<ProductEntity> productEntityList=productEntityRepository.findBySupplierId(supplierId);
        List<Product> productsList = new ArrayList<>();
        productEntityList.forEach(productEntity -> productsList.add(ProductEntityMapper.EntityToModel(productEntity)));
        return productsList;
    }
}
