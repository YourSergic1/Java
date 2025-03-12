package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.web.model.ProductDto;

public class ProductDtoMapper {
    public static ProductDto modelToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setType(product.getType());
        productDto.setSupplierId(product.getSupplierId());
        return productDto;
    }

    public static Product dtoToModel(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setType(productDto.getType());
        product.setSupplierId(productDto.getSupplierId());
        return product;
    }
}
