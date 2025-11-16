package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.enums;

public enum BotState {
    START(0, "Начало"),
    CREATE(1, "Создание заявки"),
    WAITING_NAME(2, "Ожидание имени"),
    WAITING_SURNAME(3, "Ожидание фамилии"),
    WAITING_PATRONYMIC(4, "Ожидание отчества"),
    WAITING_PASSPORT_SERIES(5, "Ожидание серии паспорта"),
    WAITING_PASSPORT_NUMBER(6, "Ожидание номера паспорта"),
    WAITING_DEPARTMENT(7, "Ожидание органа выдачи"),
    WAITING_DEPARTMENT_CODE(8, "Ожидание кода подразделения"),
    WAITING_PASSPORT_DATE(9, "Ожидание даты выдачи паспорта"),
    WAITING_PHONE(10, "Ожидание телефона"),
    WAITING_ADDRESS(11, "Ожидание адреса"),
    WAITING_CADASTRAL_NUMBER(12, "Ожидание кадастрового номера"),
    WAITING_PROJECT_START_DATE(13, "Ожидание даты начала проекта"),
    WAITING_PRIMARY_NUMBER(14, "Ожидание первичного номера"),
    WAITING_SECONDARY_NUMBER(15, "Ожидание вторичного номера"),
    WAITING_PRICE(16, "Ожидание цены"),
    WAITING_AGREEMENT_DATE(17, "Ожидание даты договора"),
    WAITING_INFO_APPROVE(18, "Ожидание подтверждения данных"),
    WAITING_CHANGE_NAME(19, "Ожидание изменения имени"),
    WAITING_CHANGE_SURNAME(19, "Ожидание изменения фамилии"),
    WAITING_CHANGE_PATRONYMIC(19, "Ожидание изменения отчества"),
    WAITING_CHANGE_PASSPORT_SERIES(19, "Ожидание изменения серии паспорта"),
    WAITING_CHANGE_PASSPORT_NUMBER(19, "Ожидание изменения номера паспорта"),
    WAITING_CHANGE_DEPARTMENT(19, "Ожидание изменения органа выдачи"),
    WAITING_CHANGE_DEPARTMENT_CODE(19, "Ожидание изменения кода подразделения"),
    WAITING_CHANGE_PASSPORT_DATE(19, "Ожидание изменения даты выдачи паспорта"),
    WAITING_CHANGE_PHONE(19, "Ожидание изменения телефона"),
    WAITING_CHANGE_ADDRESS(19, "Ожидание изменения адреса"),
    WAITING_CHANGE_CADASTRAL_NUMBER(19, "Ожидание изменения кадастрового номера"),
    WAITING_CHANGE_PROJECT_START_DATE(19, "Ожидание изменения даты начала проекта"),
    WAITING_CHANGE_PRIMARY_NUMBER(19, "Ожидание изменения первичного номера"),
    WAITING_CHANGE_SECONDARY_NUMBER(19, "Ожидание изменения вторичного номера"),
    WAITING_CHANGE_PRICE(19, "Ожидание изменения цены"),
    WAITING_CHANGE_AGREEMENT_DATE(19, "Ожидание изменения даты договора"),
    WAITING_CHANGE_INFO_APPROVE(19, "Ожидание изменения подтверждения данных");

    private final int id;
    private final String description;

    BotState(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
