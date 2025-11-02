package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Customer {
    String name;
    String surname;
    String patronymic;
    String passportSeries;
    String passportNumber;
    String department;
    String departmentCode;
    LocalDate dateOfReceiptOfPassport;
    String phoneNumber;
}
