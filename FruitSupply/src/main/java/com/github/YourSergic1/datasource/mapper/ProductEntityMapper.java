package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.ProductEntity;
import com.github.YourSergic1.domain.model.Product;

public class ProductEntityMapper {
    public static ProductEntity ModelToEntity(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        productEntity.setType(product.getType());
        productEntity.setSupplierId(product.getSupplierId());
        return productEntity;
    }

    public static Product EntityToModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setName(productEntity.getName());
        product.setPrice(productEntity.getPrice());
        product.setType(productEntity.getType());
        product.setSupplierId(productEntity.getSupplierId());
        return product;
    }
}
