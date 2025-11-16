package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.service;

import lombok.RequiredArgsConstructor;
import org.ahmetvaleev.ilnar.rauhatovich.app.exceptionHandler.exception.InputFileException;
import org.ahmetvaleev.ilnar.rauhatovich.app.exceptionHandler.exception.ResourceCloseException;
import org.ahmetvaleev.ilnar.rauhatovich.app.exceptionHandler.exception.WriteOutputException;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model.Agreement;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model.Customer;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;

import static org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.enums.Placeholder.*;

@Service
@RequiredArgsConstructor
public class DocumentCreatingService {
    private final static String INPUT_PATH = "documents/Agreement.docx";
    private final NumberToWordService numberToWordService;

    public byte[] fillAgreement(Agreement agreement) {
        ClassPathResource resource = new ClassPathResource(INPUT_PATH);
        InputStream inputStream = null;
        XWPFDocument document = null;
        ByteArrayOutputStream baos = null;

        try {
            inputStream = resource.getInputStream();
            document = new XWPFDocument(inputStream);
        } catch (IOException e) {
            throw new InputFileException(e.getMessage());
        }

        for (XWPFTable table : document.getTables()) {
            for (XWPFTableRow row : table.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph paragraph : cell.getParagraphs()) {
                        replacePlaceholderInParagraph(paragraph, agreement);
                    }
                }
            }
        }

        try {
            baos = new ByteArrayOutputStream();
            document.write(baos);
            return baos.toByteArray();
        } catch (IOException e) {
            throw new WriteOutputException(e.getMessage());
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                if (document != null) document.close();
                if (baos != null) baos.close();
            } catch (IOException e) {
                throw new ResourceCloseException(e.getMessage());
            }
        }
    }

    private void replacePlaceholderInParagraph(XWPFParagraph paragraph, Agreement agreement) {
        String fullText = paragraph.getText();
        if (fullText.contains(PN)) {
            replaceTextInRuns(paragraph,
                    PN,
                    agreement.getPrimaryContractNumber());
        }
        if (fullText.contains(SN)) {
            replaceTextInRuns(paragraph,
                    SN,
                    agreement.getSecondaryContractNumber());
        }
        if (fullText.contains(D)) {
            replaceTextInRuns(paragraph,
                    D,
                    Integer.toString(agreement.getAgreementDate().getDayOfMonth()));
        }
        if (fullText.contains(M)) {
            replaceTextInRuns(paragraph,
                    M,
                    monthNameFromNumber(agreement.getAgreementDate().getMonth()));
        }
        if (fullText.contains(Y)) {
            replaceTextInRuns(paragraph,
                    Y,
                    Integer.toString(agreement.getAgreementDate().getYear()));
        }
        if (fullText.contains(NAME)) {
            replaceTextInRuns(paragraph,
                    NAME,
                    agreement.getCustomer().getName());
        }
        if (fullText.contains(SURNAME)) {
            replaceTextInRuns(paragraph,
                    SURNAME,
                    agreement.getCustomer().getSurname());
        }
        if (fullText.contains(PATRONYMIC)) {
            replaceTextInRuns(paragraph,
                    PATRONYMIC,
                    agreement.getCustomer().getPatronymic());
        }
        if (fullText.contains(ADDRESS)) {
            replaceTextInRuns(paragraph,
                    ADDRESS,
                    agreement.getLocation().getAddress());
        }
        if (fullText.contains(CADASTRAL_NUMBER)) {
            replaceTextInRuns(paragraph,
                    CADASTRAL_NUMBER,
                    agreement.getLocation().getCadastralNumber());
        }
        if (fullText.contains(PROJECT_START_DATE)) {
            replaceTextInRuns(paragraph,
                    PROJECT_START_DATE,
                    makeDateString(agreement.getProjectStartDate()));
        }
        if (fullText.contains(PRICE)) {
            replaceTextInRuns(paragraph,
                    PRICE,
                    agreement.getPrice().toString());
        }
        if (fullText.contains(PRICE_WORD)) {
            replaceTextInRuns(paragraph,
                    PRICE_WORD,
                    numberToWordService.spellNumber(agreement.getPrice()));
        }
        if (fullText.contains(FIRST_PAYMENT)) {
            replaceTextInRuns(paragraph,
                    FIRST_PAYMENT,
                    agreement.getFirstPayment().toString());
        }
        if (fullText.contains(FIRST_PAYMENT_WORD)) {
            replaceTextInRuns(paragraph,
                    FIRST_PAYMENT_WORD,
                    numberToWordService.spellNumber(agreement.getFirstPayment()));
        }
        if (fullText.contains(SECOND_PAYMENT)) {
            replaceTextInRuns(paragraph,
                    SECOND_PAYMENT,
                    agreement.getSecondPayment().toString());
        }
        if (fullText.contains(SECOND_PAYMENT_WORD)) {
            replaceTextInRuns(paragraph,
                    SECOND_PAYMENT_WORD,
                    numberToWordService.spellNumber(agreement.getSecondPayment()));
        }
        if (fullText.contains(THIRD_PAYMENT)) {
            replaceTextInRuns(paragraph,
                    THIRD_PAYMENT,
                    agreement.getLastPayment().toString());
        }
        if (fullText.contains(THIRD_PAYMENT_WORD)) {
            replaceTextInRuns(paragraph,
                    THIRD_PAYMENT_WORD,
                    numberToWordService.spellNumber(agreement.getLastPayment()));
        }
        if (fullText.contains(FIO)) {
            replaceTextInRuns(paragraph,
                    FIO,
                    makeFio(agreement.getCustomer()));
        }
        if (fullText.contains(PASSPORT)) {
            replaceTextInRuns(paragraph,
                    PASSPORT,
                    makePassportFullNumber(agreement.getCustomer()));
        }
        if (fullText.contains(PASSPORT_DEPARTMENT)) {
            replaceTextInRuns(paragraph,
                    PASSPORT_DEPARTMENT,
                    agreement.getCustomer().getDepartment());
        }
        if (fullText.contains(DEPARTMENT_CODE)) {
            replaceTextInRuns(paragraph,
                    DEPARTMENT_CODE,
                    agreement.getCustomer().getDepartmentCode());
        }
        if (fullText.contains(PASSPORT_DATE)) {
            replaceTextInRuns(paragraph,
                    PASSPORT_DATE,
                    makeDateString(agreement.getCustomer().getDateOfReceiptOfPassport()));
        }
        if (fullText.contains(PHONE_NUMBER)) {
            replaceTextInRuns(paragraph,
                    PHONE_NUMBER,
                    agreement.getCustomer().getPhoneNumber());
        }
    }

    private void replaceTextInRuns(XWPFParagraph paragraph, String placeholder, String replacement) {
        StringBuilder fullText = new StringBuilder();
        for (XWPFRun run : paragraph.getRuns()) {
            String text = run.getText(0);
            if (text != null) {
                fullText.append(text);
            }
        }
        if (fullText.toString().contains(placeholder)) {
            String replacedText = fullText.toString().replace(placeholder, replacement);
            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
            XWPFRun newRun = paragraph.createRun();
            newRun.setText(replacedText);
            newRun.setFontSize(12);
            newRun.setFontFamily("Cambria");
        }
    }

    private String makeDateString(LocalDate date) {
        StringBuilder stringBuilder = new StringBuilder()
                .append(date.getDayOfMonth())
                .append(" ")
                .append(monthNameFromNumber(date.getMonth()))
                .append(" ")
                .append(date.getYear())
                .append(" г.");
        return stringBuilder.toString();
    }

    private String monthNameFromNumber(Month month) {
        switch (month) {
            case JANUARY:
                return "января";
            case FEBRUARY:
                return "февраля";
            case MARCH:
                return "марта";
            case APRIL:
                return "апреля";
            case MAY:
                return "мая";
            case JUNE:
                return "июня";
            case JULY:
                return "июля";
            case AUGUST:
                return "августа";
            case SEPTEMBER:
                return "сентября";
            case OCTOBER:
                return "октября";
            case NOVEMBER:
                return "ноября";
            case DECEMBER:
                return "декабря";
            default:
                return "";
        }
    }

    private String makeFio(Customer customer) {
        StringBuilder fio = new StringBuilder();
        fio.append(customer.getName()).append(" ");
        if (customer.getSurname() != null) {
            fio.append(customer.getSurname()).append(" ");
        }
        if (customer.getPatronymic() != null) {
            fio.append(customer.getPatronymic()).append(" ");
        }
        return fio.toString();
    }

    private String makePassportFullNumber(Customer customer) {
        String series = customer.getPassportSeries();
        String number = customer.getPassportNumber();
        return series + " " + number;
    }
}
