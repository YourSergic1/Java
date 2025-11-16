package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.service;

import com.ibm.icu.text.RuleBasedNumberFormat;
import com.ibm.icu.util.ULocale;
import org.springframework.stereotype.Service;

@Service
public class NumberToWordService {
    private final RuleBasedNumberFormat numberFormat;

    public NumberToWordService() {
        this.numberFormat = new RuleBasedNumberFormat(ULocale.forLanguageTag("ru-RU"), RuleBasedNumberFormat.SPELLOUT);
    }

    public String spellNumber(long number) {
        if (number == 0) return "ноль";
        return numberFormat.format(number);
    }
}
