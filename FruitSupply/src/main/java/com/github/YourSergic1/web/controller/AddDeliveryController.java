package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AddDeliveryController {
    @Autowired
    RepositoryServiceImpl repositoryServiceImpl;

    @GetMapping("/addDelivery/chooseCompany")
    public String showAddDelivery(Model model) {
        model.addAttribute("companies", repositoryServiceImpl.getAllSupplies());
        return "chooseCompany";
    }

    @PostMapping("/addDelivery/chooseCompany")
    public String redirectToChooseProduct(@RequestParam String companyName) {
        return "redirect:/addDelivery/chooseProduct?name=" + companyName;
    }

    @GetMapping("/addDelivery/chooseProduct")
    public String chooseProduct( @RequestParam String name,Model model) {
        Supplier supplier = repositoryServiceImpl.getSupplier(name);
        model.addAttribute("products", repositoryServiceImpl.findProductsBySupplierId(supplier.getId()));
        return "chooseProduct";
    }

    @PostMapping("/addDelivery/end")
    public String endChooseProduct() {
        Delivery delivery = new Delivery();
        delivery.setSupplier(new Supplier());
        return "home";
    }
}
