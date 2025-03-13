package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

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
    public String redirectToChooseProduct(@RequestParam String companyName, @RequestParam LocalDate date) {
        Delivery delivery = new Delivery();
        UUID deliveryId = UUID.randomUUID();
        delivery.setDeliveryDate(date);
        delivery.setId(deliveryId);
        delivery.setSupplier(repositoryServiceImpl.getSupplier(companyName));
        repositoryServiceImpl.addDelivery(delivery);
        return "redirect:/addDelivery/chooseProduct?deliveryID=" + deliveryId;
    }

    @GetMapping("/addDelivery/chooseProduct")
    public String chooseProduct(@RequestParam UUID deliveryID, Model model) {
        Delivery delivery = repositoryServiceImpl.getDeliveryById(deliveryID);
        model.addAttribute("deliveryID", deliveryID);
        model.addAttribute("products", repositoryServiceImpl.findProductsBySupplierId(delivery.getSupplier().getId()));
        return "chooseProduct";
    }

    @PostMapping("/addDelivery/chooseProduct")
    public String handleProductAction(
            @RequestParam UUID deliveryID,
            @RequestParam UUID productId,
            @RequestParam double productWeight,
            @RequestParam String action
    ) {
        Delivery delivery = repositoryServiceImpl.getDeliveryById(deliveryID);
        Product product = repositoryServiceImpl.getProductById(productId);
        double price = product.getPrice() * productWeight;

        List<DeliveryProduct> deliveryProducts = repositoryServiceImpl.findDeliveryProductsByDeliveryAndProduct(delivery, product);

        DeliveryProduct newDeliveryProduct = new DeliveryProduct();
        newDeliveryProduct.setId(UUID.randomUUID());
        newDeliveryProduct.setProduct(product);
        newDeliveryProduct.setWeight((float) productWeight);
        newDeliveryProduct.setPrice((float) price);
        newDeliveryProduct.setDelivery(delivery);

        for (DeliveryProduct deliveryProduct : deliveryProducts) {
            newDeliveryProduct.setPrice((float) (deliveryProduct.getPrice() + price));
            newDeliveryProduct.setWeight((float) (deliveryProduct.getWeight() + productWeight));
            repositoryServiceImpl.deleteDeliveryProduct(deliveryProduct.getId());
            break;
        }
        repositoryServiceImpl.addDeliveryProduct(newDeliveryProduct); // Сохраняем новый продукт
        if ("addMore".equals(action)) {
            return "redirect:/addDelivery/chooseProduct?deliveryID=" + deliveryID;
        }
        return "home";
    }

}

