package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.service;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public boolean validateName(String text) {
        return text.length() <= 19;
    }

    public boolean validateSurname(String text) {
        return text.length() <= 43;
    }

    public boolean validatePatronymic(String text) {
        return text.length() <= 43;
    }

    public boolean validatePassportSeries(String text) {
        if (text.length() != 4) return false;
        if (text.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassportNumber(String text) {
        if (text.length() != 6) return false;
        if (text.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validatePassportDepartmentCode(String text) {
        String codeWithoutLLine = text.replace("-", "");
        if (codeWithoutLLine.length() != 6) return false;
        if (codeWithoutLLine.matches("\\d+")) {
            return true;
        } else {
            return false;
        }
    }

    public String validateAndFormatPhone(String text) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            String numberOnlyNums = text.replaceAll("[^\\d+]", "");
            if (numberOnlyNums.isEmpty()) {
                return null;
            }
            Phonenumber.PhoneNumber number = phoneUtil.parse(numberOnlyNums, "RU");
            if (phoneUtil.isValidNumber(number)) {
                return phoneUtil.format(number, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            }
            return null;
        } catch (NumberParseException e) {
            return null;
        }
    }

    public boolean validateAddress(String text) {
        return text.length() <= 38;
    }

    public boolean validateCadastralNumber(String text) {
        return text.length() <= 23;
    }

    public boolean validateAgreementNumber(String text) {
        if (text.length() > 4) return false;
        if (text.matches("\\d+")) {
            return true;
        }
        return false;
    }

    public boolean validatePrice(String text) {
        if (text.matches("\\d+")) {
            return true;
        }
        return false;
    }

}
