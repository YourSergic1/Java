package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.controller;

import lombok.AllArgsConstructor;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model.Agreement;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.service.DocumentCreatingService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agreement")
@AllArgsConstructor
public class DocumentCreatingController {

    DocumentCreatingService documentCreatingService;

    @PostMapping("/create")
    public ResponseEntity<byte[]> documentCreating(@RequestBody Agreement agreement) {
        calculatePayments(agreement);
        byte[] docBytes = documentCreatingService.fillAgreement(agreement);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
        headers.setContentDispositionFormData("attachment",
                "Agreement_" + agreement.getPrimaryContractNumber() + "-" + agreement.getSecondaryContractNumber() + ".docx");
        headers.setContentLength(docBytes.length);
        return new ResponseEntity<>(docBytes, headers, HttpStatus.OK);
    }

    private void calculatePayments(Agreement agreement) {
        Integer price = agreement.getPrice();
        if (price == null || price < 0) {
            throw new IllegalArgumentException("Цена (price) обязательна и должна быть >= 0");
        }
        int firstPayment = (int) Math.round(price * 0.2);  // 20%
        int secondPayment = (int) Math.round(price * 0.3);  // 30%
        int lastPayment = price - firstPayment - secondPayment;  // 50%
        agreement.setFirstPayment(firstPayment);
        agreement.setSecondPayment(secondPayment);
        agreement.setLastPayment(lastPayment);
    }
}
