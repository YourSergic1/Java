package com.github.YourSergic1.datasource.mapper;

import com.github.YourSergic1.datasource.model.SupplierEntity;
import com.github.YourSergic1.domain.model.Supplier;

public class SupplierEntityMapper {

    public static Supplier EntityToModel(SupplierEntity supplierEntity) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierEntity.getId());
        supplier.setName(supplierEntity.getName());
        supplier.setAddress(supplierEntity.getAddress());
        supplier.setPhone(supplierEntity.getPhone());
        return supplier;
    }

    public static SupplierEntity ModelToEntity(Supplier supplier) {
        SupplierEntity supplierEntity = new SupplierEntity();
        supplierEntity.setId(supplier.getId());
        supplierEntity.setName(supplier.getName());
        supplierEntity.setAddress(supplier.getAddress());
        supplierEntity.setPhone(supplier.getPhone());
        return supplierEntity;
    }
}
