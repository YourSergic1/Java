package com.github.YourSergic1.datasource.model;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Getter
@Setter
public class Report {
    @Id
    UUID id;
    LocalDate startDate;
    LocalDate endDate;
    String companyName;
    Map<String, float[]> products=new HashMap<>();
}
