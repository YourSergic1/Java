package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class Agreement {
    private String primaryContractNumber;
    private String secondaryContractNumber;
    private LocalDate agreementDate;
    private LocalDate projectStartDate;
    private Integer price;
    private Integer firstPayment;
    private Integer secondPayment;
    private Integer lastPayment;
    private Customer customer;
    private Location location;

    public Agreement() {
        customer = new Customer();
        location = new Location();
    }
}
