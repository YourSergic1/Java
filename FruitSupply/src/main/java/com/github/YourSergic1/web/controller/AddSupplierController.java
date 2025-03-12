package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AddSupplierController {
    @Autowired
    RepositoryServiceImpl repositoryServiceImpl;

    @GetMapping("/addSupplier")
    public String addSupplierShow() {
        return "addSupplier";
    }

    @PostMapping("/addSupplier")
    public String addSupplier(@RequestParam String companyName,
                              @RequestParam String companyAddress,
                              @RequestParam String companyPhone) {
        Supplier supplier = new Supplier(UUID.randomUUID(),companyName ,companyAddress,companyPhone);
        repositoryServiceImpl.addSupplier(supplier);
        return "home";
    }
}
