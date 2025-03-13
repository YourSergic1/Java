package com.github.YourSergic1.datasource.service;

import com.github.YourSergic1.datasource.mapper.DeliveryEntityMapper;
import com.github.YourSergic1.datasource.mapper.DeliveryProductEntityMapper;
import com.github.YourSergic1.datasource.mapper.ProductEntityMapper;
import com.github.YourSergic1.datasource.mapper.SupplierEntityMapper;
import com.github.YourSergic1.datasource.model.DeliveryEntity;
import com.github.YourSergic1.datasource.model.DeliveryProductEntity;
import com.github.YourSergic1.datasource.model.ProductEntity;
import com.github.YourSergic1.datasource.model.SupplierEntity;
import com.github.YourSergic1.datasource.repository.DeliveryProductRepository;
import com.github.YourSergic1.datasource.repository.DeliveryRepository;
import com.github.YourSergic1.datasource.repository.ProductEntityRepository;
import com.github.YourSergic1.datasource.repository.SupplierEntityRepository;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    RepositoryServiceImpl(DeliveryProductRepository deliveryProductRepository, DeliveryRepository deliveryRepository, ProductEntityRepository productEntityRepository, SupplierEntityRepository supplierEntityRepository) {
        this.deliveryProductRepository = deliveryProductRepository;
        this.deliveryRepository = deliveryRepository;
        this.productEntityRepository = productEntityRepository;
        this.supplierEntityRepository = supplierEntityRepository;
    }

    @Override
    public void addSupplier(Supplier supplier) {
        SupplierEntity supplierEntity = SupplierEntityMapper.ModelToEntity(supplier);
        supplierEntityRepository.save(supplierEntity);
    }

    @Override
    public void addProduct(Product product) {
        ProductEntity productEntity = ProductEntityMapper.ModelToEntity(product);
        productEntityRepository.save(productEntity);
    }

    @Override
    public void addDelivery(Delivery delivery) {
        DeliveryEntity deliveryEntity = DeliveryEntityMapper.ModelToEntity(delivery);
        deliveryRepository.save(deliveryEntity);
    }

    @Override
    public void addDeliveryProduct(DeliveryProduct deliveryProduct) {
        DeliveryProductEntity deliveryProductEntity = DeliveryProductEntityMapper.modelToEntity(deliveryProduct);
        deliveryProductRepository.save(deliveryProductEntity);
    }

    @Override
    public void deleteDeliveryProduct(UUID id) {
        deliveryProductRepository.deleteById(id);
    }

    @Override
    public List<Supplier> getAllSupplies() {
        List<SupplierEntity> supplierEntities = supplierEntityRepository.findAll();
        List<Supplier> suppliers = new ArrayList<>();
        supplierEntities.forEach(supplierEntity -> suppliers.add(SupplierEntityMapper.EntityToModel(supplierEntity)));
        return suppliers;
    }

    @Override
    public Supplier getSupplier(String name) {
        Optional<SupplierEntity> supplierEntity = supplierEntityRepository.findByName(name);
        return supplierEntity.map(SupplierEntityMapper::EntityToModel).orElse(null);
    }

    @Override
    public Supplier getSupplier(UUID id) {
        Optional<SupplierEntity> supplierEntity = supplierEntityRepository.findById(id);
        return supplierEntity.map(SupplierEntityMapper::EntityToModel).orElse(null);
    }

    @Override
    public List<Product> findProductsBySupplierId(UUID supplierId) {
        List<ProductEntity> productEntityList = productEntityRepository.findBySupplierId(supplierId);
        List<Product> productsList = new ArrayList<>();
        productEntityList.forEach(productEntity -> productsList.add(ProductEntityMapper.EntityToModel(productEntity)));
        return productsList;
    }

    @Override
    public Delivery getDeliveryById(UUID id) {
        DeliveryEntity deliveryEntity = deliveryRepository.getDeliveryEntityById(id);
        return DeliveryEntityMapper.EntityToModel(deliveryEntity);
    }

    @Override
    public Product getProductById(UUID id) {
        return ProductEntityMapper.EntityToModel(productEntityRepository.findById(id).get());
    }

    @Override
    public List<DeliveryProduct> findDeliveryProductsByDeliveryAndProduct(Delivery delivery, Product product) {
        List<DeliveryProductEntity> deliveryProductEntityList = deliveryProductRepository.findAllByDeliveryAndProductEntity(DeliveryEntityMapper.ModelToEntity(delivery), ProductEntityMapper.ModelToEntity(product));
        List<DeliveryProduct> deliveryProducts = new ArrayList<>();
        deliveryProductEntityList.forEach(deliveryProductEntity -> deliveryProducts.add(DeliveryProductEntityMapper.EntityToModel(deliveryProductEntity)));
        return deliveryProducts;
    }

    @Override
    public List<Delivery> deliveriesBetweenDatesAndBySupply(Supplier supplier, LocalDate from, LocalDate to) {
        List<DeliveryEntity> deliveryEntities = deliveryRepository.getAllBySupplierAndDeliveryDateAfterAndDeliveryDateBefore(SupplierEntityMapper.ModelToEntity(supplier), from, to);
        List<Delivery> deliveryList = new ArrayList<>();
        deliveryEntities.forEach(deliveryEntity -> deliveryList.add(DeliveryEntityMapper.EntityToModel(deliveryEntity)));
        return deliveryList;
    }

    @Override
    public List<DeliveryProduct> findDeliveryProductsByDelivery(Delivery delivery) {
        List<DeliveryProductEntity> deliveryProductEntityList = deliveryProductRepository.findAllByDelivery(DeliveryEntityMapper.ModelToEntity(delivery));
        List<DeliveryProduct> deliveryProducts = new ArrayList<>();
        deliveryProductEntityList.forEach(deliveryProductEntity -> deliveryProducts.add(DeliveryProductEntityMapper.EntityToModel(deliveryProductEntity)));
        return deliveryProducts;
    }
}
