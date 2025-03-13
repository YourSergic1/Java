package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class AddProductController {
    @Autowired
    RepositoryServiceImpl repositoryServiceImpl;

    @GetMapping("/addProduct")
    public String addProductShow(Model model) {
        model.addAttribute("companies", repositoryServiceImpl.getAllSupplies());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam String companyName,
                             @RequestParam String productType,
                             @RequestParam String productName,
                             @RequestParam float productPrice) {
        Supplier supplier = repositoryServiceImpl.getSupplier(companyName);
        Product product = new Product(UUID.randomUUID(), productName, productType, productPrice, supplier.getId());
        repositoryServiceImpl.addProduct(product);
        return "home";
    }
}
