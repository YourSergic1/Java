package com.github.YourSergic1.web.mapper;

import com.github.YourSergic1.domain.model.Supplier;
import com.github.YourSergic1.web.model.SupplierDto;

public class SupplierDtoMapper {
    public static SupplierDto ModelToDto(Supplier supplier) {
        SupplierDto supplierDto = new SupplierDto();
        supplierDto.setId(supplier.getId());
        supplierDto.setName(supplier.getName());
        supplierDto.setAddress(supplier.getAddress());
        supplierDto.setPhone(supplier.getPhone());
        return supplierDto;
    }

    public static Supplier DtoToModel(SupplierDto supplierDto) {
        Supplier supplier = new Supplier();
        supplier.setId(supplierDto.getId());
        supplier.setName(supplierDto.getName());
        supplier.setAddress(supplierDto.getAddress());
        supplier.setPhone(supplierDto.getPhone());
        return supplier;
    }
}
