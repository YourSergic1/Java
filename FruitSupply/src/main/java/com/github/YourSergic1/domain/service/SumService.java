package com.github.YourSergic1.domain.service;

import com.github.YourSergic1.datasource.model.Report;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SumService {
    private final RepositoryServiceImpl repositoryService;

    @Autowired
    public SumService(RepositoryServiceImpl repositoryService) {
        this.repositoryService = repositoryService;
    }

    public Report sum(List<Delivery> deliveries){
        List<DeliveryProduct> allDeliverProducts = new ArrayList<>();
        for (Delivery delivery : deliveries) {
            allDeliverProducts.addAll(repositoryService.findDeliveryProductsByDelivery(delivery));
        }
        Report report = new Report();
        report.setId(UUID.randomUUID());
        report.setStartDate(null);
        report.setEndDate(null);
        report.setCompanyName(null);
        for (DeliveryProduct deliveryProduct : allDeliverProducts) {
            if (!report.getProducts().containsKey(deliveryProduct.getProduct().getName())) {
                report.getProducts().put(deliveryProduct.getProduct().getName(), new float[2]);
            }
        }
        for (DeliveryProduct deliveryProduct : allDeliverProducts) {
            String name = deliveryProduct.getProduct().getName();
            float[] mem=report.getProducts().get(name);
            float[] res= new float[2];
            res[0]=deliveryProduct.getWeight()+mem[0];
            res[1]=deliveryProduct.getPrice()+mem[1];
            report.getProducts().put(name,res);
        }
        return report;
    }
}
