package com.github.YourSergic1.web.controller;

import com.github.YourSergic1.datasource.model.Report;
import com.github.YourSergic1.datasource.service.RepositoryServiceImpl;
import com.github.YourSergic1.domain.model.Delivery;
import com.github.YourSergic1.domain.model.DeliveryProduct;
import com.github.YourSergic1.domain.model.Product;
import com.github.YourSergic1.domain.model.Supplier;
import com.github.YourSergic1.domain.service.SumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ChooseInfoForReport {
    @Autowired
    RepositoryServiceImpl repositoryServiceImpl;
    @Autowired
    SumService sumService;

    @GetMapping("/chooseInfoForReport")
    public String chooseInfoForReport(Model model) {
        model.addAttribute("companies", repositoryServiceImpl.getAllSupplies());
        return "chooseInfoForReport";
    }

    @PostMapping("/chooseInfoForReport")
    public String redirectToResult(@RequestParam String companyName,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd) {
        String url = String.format(
                "redirect:/chooseInfoForReport/report?companyName=%s&dateStart=%s&dateEnd=%s",
                companyName,
                dateStart,
                dateEnd
        );

        return url;
    }

    @GetMapping("/chooseInfoForReport/report")
    public String report(@RequestParam String companyName,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStart,
                         @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateEnd,
                         Model model) {


        // Логика обработки
        Supplier supplier = repositoryServiceImpl.getSupplier(companyName);
        List<Delivery> deliveries = repositoryServiceImpl.deliveriesBetweenDatesAndBySupply(supplier, dateStart, dateEnd);
        Report res = sumService.sum(deliveries);
        res.setStartDate(dateStart);
        res.setEndDate(dateEnd);
        res.setCompanyName(companyName);
        model.addAttribute("res", res);
        return "report";
    }

}
