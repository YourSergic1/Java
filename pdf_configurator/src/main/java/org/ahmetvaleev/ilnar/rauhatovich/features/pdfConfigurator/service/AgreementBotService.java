package org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.service;

import lombok.RequiredArgsConstructor;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.enums.BotState;
import org.ahmetvaleev.ilnar.rauhatovich.features.pdfConfigurator.model.Agreement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AgreementBotService extends TelegramLongPollingBot {
    @Value("${telegrambot.username}")
    private String botUsername;

    @Value("${telegrambot.token}")
    private String botToken;

    @Value("${app.api.url}")
    private String apiUrl;

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    DocumentCreatingService documentCreatingService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final Map<Long, BotState> userStates = new HashMap<>();
    private final Map<Long, Agreement> userData = new HashMap<>();
    private final Map<Long, Integer> lastButtonMessageIds = new HashMap<>();

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String callbackData = callbackQuery.getData();
            long chatId = callbackQuery.getMessage().getChatId();
            BotState currentState = userStates.getOrDefault(chatId, BotState.START);
            removePreviousKeyboard(chatId);
            if ("/cancel".equals(callbackData)) {
                userStates.put(chatId, BotState.START);
                userData.remove(chatId);
                sendMessageWithCreate(chatId, "Создать новый договор?");
                return;
            }
            if ("/approve".equals(callbackData)) {
                createDocument(chatId);
                userStates.put(chatId, BotState.START);
                userData.remove(chatId);
                sendMessageWithCreate(chatId, "Создать новый договор?");
                return;
            }
            if ("/create".equals(callbackData)) {
                if (currentState != BotState.START) {
                    sendMessageResumeAndCancel(chatId, "Сначала завершите текущий сбор или  отмените его.");
                    return;
                }
                Agreement agreement = new Agreement();
                userData.put(chatId, agreement);
                userStates.put(chatId, BotState.CREATE);
                create(chatId);
                return;
            }
            BotState changeData = changeData(callbackData, chatId);
            if (changeData != null) {
                userStates.put(chatId, changeData);
                return;
            }
            if ("/back".equals(callbackData)) {
                if (currentState.getId() == 19) {
                    showSummary(chatId);
                    userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
                    return;
                }
                currentState.getId();
                userStates.put(chatId, getById(currentState.getId() - 1));
                sendMessageOnBackStep(chatId, userStates.get(chatId));
            }
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();
            BotState currentState = userStates.getOrDefault(chatId, BotState.START);
            removePreviousKeyboard(chatId);

            if ("/start".equals(messageText)) {
                userStates.put(chatId, BotState.START);
                userData.remove(chatId);
                sendMessageWithCreate(chatId, "Создать новый договор?");
                return;
            }
            switch (currentState) {
                case START:
                    start(chatId);
                    break;
                case WAITING_NAME, WAITING_CHANGE_NAME:
                    setName(chatId, messageText, currentState);
                    break;
                case WAITING_SURNAME, WAITING_CHANGE_SURNAME:
                    setSurname(chatId, messageText, currentState);
                    break;
                case WAITING_PATRONYMIC, WAITING_CHANGE_PATRONYMIC:
                    setPatronymic(chatId, messageText, currentState);
                    break;
                case WAITING_PASSPORT_SERIES, WAITING_CHANGE_PASSPORT_SERIES:
                    setPassportSeries(chatId, messageText, currentState);
                    break;
                case WAITING_PASSPORT_NUMBER, WAITING_CHANGE_PASSPORT_NUMBER:
                    setPassportNumber(chatId, messageText, currentState);
                    break;
                case WAITING_DEPARTMENT, WAITING_CHANGE_DEPARTMENT:
                    setPassportDepartment(chatId, messageText, currentState);
                    break;
                case WAITING_DEPARTMENT_CODE, WAITING_CHANGE_DEPARTMENT_CODE:
                    setPassportDepartmentCode(chatId, messageText, currentState);
                    break;
                case WAITING_PASSPORT_DATE, WAITING_CHANGE_PASSPORT_DATE:
                    setPassportDate(chatId, messageText, currentState);
                    break;
                case WAITING_PHONE, WAITING_CHANGE_PHONE:
                    setPhoneNumber(chatId, messageText, currentState);
                    break;
                case WAITING_ADDRESS, WAITING_CHANGE_ADDRESS:
                    setAddress(chatId, messageText, currentState);
                    break;
                case WAITING_CADASTRAL_NUMBER, WAITING_CHANGE_CADASTRAL_NUMBER:
                    setCadastralNumber(chatId, messageText, currentState);
                    ;
                    break;
                case WAITING_PROJECT_START_DATE, WAITING_CHANGE_PROJECT_START_DATE:
                    setProjectStartDate(chatId, messageText, currentState);
                    break;
                case WAITING_PRIMARY_NUMBER, WAITING_CHANGE_PRIMARY_NUMBER:
                    setPrimaryNumber(chatId, messageText, currentState);
                    break;
                case WAITING_SECONDARY_NUMBER, WAITING_CHANGE_SECONDARY_NUMBER:
                    setSecondaryNumber(chatId, messageText, currentState);
                    break;
                case WAITING_PRICE, WAITING_CHANGE_PRICE:
                    setPrice(chatId, messageText, currentState);
                    break;
                case WAITING_AGREEMENT_DATE, WAITING_CHANGE_AGREEMENT_DATE:
                    setAgreementDate(chatId, messageText, currentState);
                    break;
                default:
                    sendMessageWithCreate(chatId, "Создать новый договор?");
            }
        }
    }

    private void sendMessageOnBackStep(long chatId, BotState currentState) {
        switch (currentState) {
            case WAITING_NAME:
                sendMessageWithCancelButton(chatId, "ВВЕДИТЕ ИМЯ КЛИЕНТА.");
                break;
            case WAITING_SURNAME:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ФАМИЛИЮ КЛИЕНТА.");
                break;
            case WAITING_PATRONYMIC:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ОТЧЕСТВО КЛИЕНТА.");
                break;
            case WAITING_PASSPORT_SERIES:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ СЕРИЮ ПАСПОРТА (4 цифры).");
                break;
            case WAITING_PASSPORT_NUMBER:
                sendMessageWithCancelAndBackButton(chatId, "  ВВЕДИТЕ НОМЕР ПАСПОРТА (6 цифр).");
                break;
            case WAITING_DEPARTMENT:
                sendMessageWithCancelAndBackButton(chatId, "  ВВЕДИТЕ МЕСТО ВЫДАЧИ ПАСПОРТА.");
                break;
            case WAITING_DEPARTMENT_CODE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ КОД ДЕПАРТАМЕНТА (XXX-XXX).");
                break;
            case WAITING_PASSPORT_DATE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ ВЫДАЧИ ПАСПОТА ДД-ММ-ГГГГ.");
                break;
            case WAITING_PHONE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ НОМЕР ТЕЛЕФОНА.");
                break;
            case WAITING_ADDRESS:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ АДРЕС ОБЪЕКТА.");
                break;
            case WAITING_CADASTRAL_NUMBER:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ КАДАСТРОВЫЙ НОМЕР.");
                break;
            case WAITING_PROJECT_START_DATE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ НАЧАЛА РАБОТ ДД-ММ-ГГГГ.");
                break;
            case WAITING_PRIMARY_NUMBER:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ПЕРВИЧНЫЙ НОМЕР ДОГОВОРА.");
                break;
            case WAITING_SECONDARY_NUMBER:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ВТОРИЧНЫЙ НОМЕР ДОГОВОРА.");
                break;
            case WAITING_PRICE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ СТОИМОСТЬ.");
                break;
            case WAITING_AGREEMENT_DATE:
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ ЗАКЛЮЧЕНИЯ ДОГОВОРА ДД-ММ-ГГГГ.");
        }
    }

    private void start(long chatId) {
        sendMessageWithCreate(chatId, "Создать новый договор?");
        userStates.put(chatId, BotState.START);
    }

    private void create(long chatId) {
        sendMessageWithCancelButton(chatId, "ВВЕДИТЕ ИМЯ КЛИЕНТА.");
        userStates.put(chatId, BotState.WAITING_NAME);
    }

    private void setName(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateName(messageText)) {
            userData.get(chatId).getCustomer().setName(messageText);
            if (currentState == BotState.WAITING_NAME) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ФАМИЛИЮ КЛИЕНТА.");
                userStates.put(chatId, BotState.WAITING_SURNAME);
            } else if (currentState == BotState.WAITING_CHANGE_NAME) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    СЛИШКОМ ДЛИННОЕ ИМЯ!
                    ВВЕДИТЕ ИМЯ КЛИЕНТА.""");
        }
    }

    private void setSurname(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateSurname(messageText)) {
            userData.get(chatId).getCustomer().setSurname(messageText);
            if (currentState == BotState.WAITING_SURNAME) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ОТЧЕСТВО КЛИЕНТА.");
                userStates.put(chatId, BotState.WAITING_PATRONYMIC);
            } else if (currentState == BotState.WAITING_CHANGE_SURNAME) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    СЛИШКОМ ДЛИННАЯ ФАМИЛИЯ!
                    ВВЕДИТЕ ФАМИЛИЮ КЛИЕНТА.""");
        }
    }

    private void setPatronymic(long chatId, String messageText, BotState currentState) {
        if (validatorService.validatePatronymic(messageText)) {
            userData.get(chatId).getCustomer().setPatronymic(messageText);
            if (currentState == BotState.WAITING_PATRONYMIC) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ СЕРИЮ ПАСПОРТА (4 цифры).");
                userStates.put(chatId, BotState.WAITING_PASSPORT_SERIES);
            } else if (currentState == BotState.WAITING_CHANGE_PATRONYMIC) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    СЛИШКОМ ДЛИННОЕ ОТЧЕСТВО!
                    ВВЕДИТЕ ОТЧЕСТВО КЛИЕНТА.""");
        }
    }

    private void setPassportSeries(long chatId, String messageText, BotState currentState) {
        if (validatorService.validatePassportSeries(messageText)) {
            userData.get(chatId).getCustomer().setPassportSeries(messageText);
            if (currentState == BotState.WAITING_PASSPORT_SERIES) {
                sendMessageWithCancelAndBackButton(chatId, "  ВВЕДИТЕ НОМЕР ПАСПОРТА (6 цифр).");
                userStates.put(chatId, BotState.WAITING_PASSPORT_NUMBER);
            } else if (currentState == BotState.WAITING_CHANGE_PASSPORT_SERIES) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНАЯ СЕРИЯ ПАСПОРТА!
                    ВВЕДИТЕ СЕРИЮ ПАСПОРТА (4 цифры).""");
        }
    }

    private void setPassportNumber(long chatId, String messageText, BotState currentState) {
        if (validatorService.validatePassportNumber(messageText)) {
            userData.get(chatId).getCustomer().setPassportNumber(messageText);
            if (currentState == BotState.WAITING_PASSPORT_NUMBER) {
                sendMessageWithCancelAndBackButton(chatId, "  ВВЕДИТЕ МЕСТО ВЫДАЧИ ПАСПОРТА.");
                userStates.put(chatId, BotState.WAITING_DEPARTMENT);
            } else if (currentState == BotState.WAITING_CHANGE_PASSPORT_NUMBER) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНАЯ НОМЕР ПАСПОРТА!
                    ВВЕДИТЕ НОМЕР ПАСПОРТА (6 цифр).""");
        }
    }


    private void setPassportDepartment(long chatId, String messageText, BotState currentState) {
        userData.get(chatId).getCustomer().setDepartment(messageText);
        if (currentState == BotState.WAITING_DEPARTMENT) {
            sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ КОД ДЕПАРТАМЕНТА (XXX-XXX).");
            userStates.put(chatId, BotState.WAITING_DEPARTMENT_CODE);
        } else if (currentState == BotState.WAITING_CHANGE_DEPARTMENT) {
            showSummary(chatId);
            userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
        }
    }

    private void setPassportDepartmentCode(long chatId, String messageText, BotState currentState) {
        if (validatorService.validatePassportDepartmentCode(messageText)) {
            userData.get(chatId).getCustomer().setDepartmentCode(messageText);
            if (currentState == BotState.WAITING_DEPARTMENT_CODE) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ ВЫДАЧИ ПАСПОТА ДД-ММ-ГГГГ.");
                userStates.put(chatId, BotState.WAITING_PASSPORT_DATE);
            } else if (currentState == BotState.WAITING_CHANGE_DEPARTMENT_CODE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНЫЙ КОД ДЕПАРТАМЕНТА!
                    ВВЕДИТЕ КОД ДЕПАРТАМЕНТА (XXX-XXX).""");
        }
    }

    private void setPassportDate(long chatId, String messageText, BotState currentState) {
        try {
            LocalDate date = LocalDate.parse(messageText, formatter);
            userData.get(chatId).getCustomer().setDateOfReceiptOfPassport(date);
            if (currentState == BotState.WAITING_PASSPORT_DATE) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ НОМЕР ТЕЛЕФОНА.");
                userStates.put(chatId, BotState.WAITING_PHONE);
            } else if (currentState == BotState.WAITING_CHANGE_PASSPORT_DATE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } catch (Exception e) {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНАЯ ДАТА!
                    ВВЕДИТЕ ДАТУ ВЫДАЧИ ПАСПОТА ДД-ММ-ГГГГ.""");
        }
    }

    private void setPhoneNumber(long chatId, String messageText, BotState currentState) {
        String formattedPhone = validatorService.validateAndFormatPhone(messageText);
        if (formattedPhone != null) {
            userData.get(chatId).getCustomer().setPhoneNumber(formattedPhone);
            if (currentState == BotState.WAITING_PHONE) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ АДРЕС ОБЪЕКТА.");
                userStates.put(chatId, BotState.WAITING_ADDRESS);
            } else if (currentState == BotState.WAITING_CHANGE_PHONE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНЫЙ НОМЕР ТЕЛЕФОНА ИЛИ ФОРМАТ!
                    ВВЕДИТЕ НОМЕР В ЛЮБОМ ФОРМАТЕ.""");
        }
    }

    private void setAddress(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateAddress(messageText)) {
            userData.get(chatId).getLocation().setAddress(messageText);
            if (currentState == BotState.WAITING_ADDRESS) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ КАДАСТРОВЫЙ НОМЕР.");
                userStates.put(chatId, BotState.WAITING_CADASTRAL_NUMBER);
            } else if (currentState == BotState.WAITING_CHANGE_ADDRESS) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНЫЙ КОД ДЕПАРТАМЕНТА!
                    ВВЕДИТЕ АДРЕС ОБЪЕКТА.""");
        }
    }

    private void setCadastralNumber(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateCadastralNumber(messageText)) {
            userData.get(chatId).getLocation().setCadastralNumber(messageText);
            if (currentState == BotState.WAITING_CADASTRAL_NUMBER) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ НАЧАЛА РАБОТ ДД-ММ-ГГГГ.");
                userStates.put(chatId, BotState.WAITING_PROJECT_START_DATE);
            } else if (currentState == BotState.WAITING_CHANGE_CADASTRAL_NUMBER) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessage(chatId, """
                    НЕВЕРНЫЙ КОД ДЕПАРТАМЕНТА!
                    ВВЕДИТЕ КАДАСТРОВЫЙ НОМЕР.""");
        }
    }

    private void setProjectStartDate(long chatId, String messageText, BotState currentState) {
        try {
            LocalDate date = LocalDate.parse(messageText.trim(), formatter);
            userData.get(chatId).setProjectStartDate(date);
            if (currentState == BotState.WAITING_PROJECT_START_DATE) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ПЕРВИЧНЫЙ НОМЕР ДОГОВОРА.");
                userStates.put(chatId, BotState.WAITING_PRIMARY_NUMBER);
            } else if (currentState == BotState.WAITING_CHANGE_PROJECT_START_DATE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } catch (Exception e) {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНАЯ ДАТА!
                    ВВЕДИТЕ ДАТУ НАЧАЛА РАБОТ ДД-ММ-ГГГГ.""");
        }
    }


    private void setPrimaryNumber(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateAgreementNumber(messageText)) {
            userData.get(chatId).setPrimaryContractNumber(messageText);
            if (currentState == BotState.WAITING_PRIMARY_NUMBER) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ВТОРИЧНЫЙ НОМЕР ДОГОВОРА.");
                userStates.put(chatId, BotState.WAITING_SECONDARY_NUMBER);
            } else if (currentState == BotState.WAITING_CHANGE_PRIMARY_NUMBER) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНЫЙ ПЕРВИЧНЫЙ НОМЕР ДОГОВОРА!
                    ВВЕДИТЕ ПЕРВИЧНЫЙ НОМЕР ДОГОВОРА.""");
        }
    }

    private void setSecondaryNumber(long chatId, String messageText, BotState currentState) {
        if (validatorService.validateAgreementNumber(messageText)) {
            userData.get(chatId).setSecondaryContractNumber(messageText);
            if (currentState == BotState.WAITING_SECONDARY_NUMBER) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ СТОИМОСТЬ.");
                userStates.put(chatId, BotState.WAITING_PRICE);
            } else if (currentState == BotState.WAITING_CHANGE_SECONDARY_NUMBER) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    НЕВЕРНЫЙ ВТОРИЧНЫЙ НОМЕР ДОГОВОРА!
                    ВВЕДИТЕ ВТОРИЧНЫЙ НОМЕР ДОГОВОРА.""");
        }
    }

    private void setPrice(long chatId, String messageText, BotState currentState) {
        if (validatorService.validatePrice(messageText)) {
            userData.get(chatId).setPrice(Integer.valueOf(messageText));
            if (currentState == BotState.WAITING_PRICE) {
                sendMessageWithCancelAndBackButton(chatId, "ВВЕДИТЕ ДАТУ ЗАКЛЮЧЕНИЯ ДОГОВОРА ДД-ММ-ГГГГ.");
                userStates.put(chatId, BotState.WAITING_AGREEMENT_DATE);
            } else if (currentState == BotState.WAITING_CHANGE_PRICE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } else {
            sendMessageWithCancelAndBackButton(chatId, """
                    ЦЕНА ИМЕЕТ ЧИСЛОВОЙ ФОРМАТ!
                    ВВЕДИТЕ СТОИМОСТЬ.""");
        }
    }

    private void setAgreementDate(long chatId, String messageText, BotState currentState) {
        try {
            LocalDate date = LocalDate.parse(messageText.trim(), formatter);
            userData.get(chatId).setAgreementDate(date);
            if (currentState == BotState.WAITING_AGREEMENT_DATE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            } else if (currentState == BotState.WAITING_CHANGE_AGREEMENT_DATE) {
                showSummary(chatId);
                userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
            }
        } catch (Exception e) {
            sendMessage(chatId, """
                    НЕВЕРНАЯ ДАТА!
                    ВВЕДИТЕ ДАТУ ЗАКЛЮЧЕНИЯ ДОГОВОРА ДД-ММ-ГГГГ.""");
        }
    }

    private void showSummary(long chatId) {
        sendMessage(chatId, "Подтвердите данные.");
        Agreement data = userData.get(chatId);
        if (data == null || data.getCustomer() == null) {
            sendMessageWithCreate(chatId, "Создать новый договор?");
            return;
        }

        StringBuilder summary = new StringBuilder("🔍 **ВВЕДЕННЫЕ ДАННЫЕ:**\n\n"); // Markdown для жирности
        summary.append("**Имя:** ").append(data.getCustomer().getName() != null ? data.getCustomer().getName() : "---").append("\n");
        summary.append("**Фамилия:** ").append(data.getCustomer().getSurname() != null ? data.getCustomer().getSurname() : "---").append("\n");
        summary.append("**Отчество:** ").append(data.getCustomer().getPatronymic() != null ? data.getCustomer().getPatronymic() : "--- / н/д").append("\n");
        summary.append("**Серия паспорта:** ").append(data.getCustomer().getPassportSeries() != null ? data.getCustomer().getPassportSeries() : "---").append("\n");
        summary.append("**Номер паспорта:** ").append(data.getCustomer().getPassportNumber() != null ? data.getCustomer().getPassportNumber() : "---").append("\n");
        summary.append("**Орган выдачи:** ").append(data.getCustomer().getDepartment() != null ? data.getCustomer().getDepartment() : "---").append("\n");
        summary.append("**Код подразделения:** ").append(data.getCustomer().getDepartmentCode() != null ? data.getCustomer().getDepartmentCode() : "---").append("\n");
        summary.append("**Дата выдачи паспорта:** ").append(data.getCustomer().getDateOfReceiptOfPassport() != null ? data.getCustomer().getDateOfReceiptOfPassport() : "---").append("\n");
        summary.append("**Телефон:** ").append(data.getCustomer().getPhoneNumber() != null ? data.getCustomer().getPhoneNumber() : "---").append("\n");
        summary.append("**Адрес:** ").append(data.getLocation().getAddress() != null ? data.getLocation().getAddress() : "---").append("\n");
        summary.append("**Кадастровый номер:** ").append(data.getLocation().getCadastralNumber() != null ? data.getLocation().getCadastralNumber() : "---").append("\n");
        summary.append("**Дата начала проекта:** ").append(data.getProjectStartDate() != null ? data.getProjectStartDate() : "---").append("\n");
        summary.append("**Первичный номер:** ").append(data.getPrimaryContractNumber() != null ? data.getPrimaryContractNumber() : "---").append("\n");
        summary.append("**Вторичный номер:** ").append(data.getSecondaryContractNumber() != null ? data.getSecondaryContractNumber() : "---").append("\n");
        summary.append("**Цена:** ").append(data.getPrice() != null ? data.getPrice() : "---").append("\n");
        summary.append("**Дата создания договора:** ").append(data.getAgreementDate() != null ? data.getAgreementDate() : "---").append("\n");

        InlineKeyboardMarkup markup = createEditKeyboard();

        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(summary.toString())
                .parseMode("Markdown") // Для жирности (**)
                .replyMarkup(markup)
                .build();

        try {
            Message result = execute(message);
            lastButtonMessageIds.put(chatId, result.getMessageId());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        userStates.put(chatId, BotState.WAITING_INFO_APPROVE);
    }

    private void createDocument(long chatId) {
        Agreement agreement = userData.get(chatId);
        try {
            calculatePayments(agreement);
            byte[] docBytes = documentCreatingService.fillAgreement(agreement);

            String filename = "Agreement_" + agreement.getPrimaryContractNumber() + "-" +
                    agreement.getSecondaryContractNumber() + ".docx";
            InputFile inputFile = new InputFile(new ByteArrayInputStream(docBytes), filename);
            SendDocument sendDocument = SendDocument.builder()
                    .chatId(String.valueOf(chatId))
                    .document(inputFile)
                    .build();
            execute(sendDocument);
        } catch (Exception e) {
            sendMessage(chatId, "Ошибка создания: " + e.getMessage());
        }
        userData.remove(chatId);
        userStates.remove(chatId);
    }

    private void sendMessage(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();
        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
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

    private Integer sendMessageWithCancelButton(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();

        InlineKeyboardButton cancelButton = InlineKeyboardButton.builder()
                .text("❌ Отмена")
                .callbackData("/cancel")
                .build();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(cancelButton);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        message.setReplyMarkup(markup);

        try {
            Message result = execute(message);
            int messageId = result.getMessageId();
            lastButtonMessageIds.put(chatId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer sendMessageWithCancelAndBackButton(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();

        InlineKeyboardButton cancelButton = InlineKeyboardButton.builder()
                .text("❌ Отмена")
                .callbackData("/cancel")
                .build();
        InlineKeyboardButton backButton = InlineKeyboardButton.builder()
                .text("\uD83D\uDD19 Назад")
                .callbackData("/back")
                .build();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(backButton);
        row.add(cancelButton);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        message.setReplyMarkup(markup);

        try {
            Message result = execute(message);
            int messageId = result.getMessageId();
            lastButtonMessageIds.put(chatId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer sendMessageWithBackButton(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();

        InlineKeyboardButton backButton = InlineKeyboardButton.builder()
                .text("\uD83D\uDD19 Назад")
                .callbackData("/back")
                .build();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(backButton);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        message.setReplyMarkup(markup);

        try {
            Message result = execute(message);
            int messageId = result.getMessageId();
            lastButtonMessageIds.put(chatId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer sendMessageWithCreate(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();

        InlineKeyboardButton createButton = InlineKeyboardButton.builder()
                .text("\uD83D\uDCC4 Создать договор")
                .callbackData("/create")
                .build();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(createButton);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        message.setReplyMarkup(markup);

        try {
            Message result = execute(message);
            int messageId = result.getMessageId();
            lastButtonMessageIds.put(chatId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Integer sendMessageResumeAndCancel(long chatId, String text) {
        SendMessage message = SendMessage.builder()
                .chatId(String.valueOf(chatId))
                .text(text)
                .build();

        InlineKeyboardButton cancelButton = InlineKeyboardButton.builder()
                .text("❌ Отмена")
                .callbackData("/cancel")
                .build();
        InlineKeyboardButton resumeButton = InlineKeyboardButton.builder()
                .text("➡\uFE0F Продолжить")
                .callbackData("/create")
                .build();
        List<InlineKeyboardButton> row = new ArrayList<>();
        row.add(cancelButton);
        row.add(resumeButton);
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        keyboard.add(row);
        InlineKeyboardMarkup markup = InlineKeyboardMarkup.builder()
                .keyboard(keyboard)
                .build();
        message.setReplyMarkup(markup);

        try {
            Message result = execute(message);
            int messageId = result.getMessageId();
            lastButtonMessageIds.put(chatId, messageId);
            return messageId;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void removePreviousKeyboard(long chatId) {
        Integer previousMessageId = lastButtonMessageIds.remove(chatId);
        if (previousMessageId != null) {
            EditMessageReplyMarkup editMarkup = EditMessageReplyMarkup.builder()
                    .chatId(chatId)
                    .messageId(previousMessageId)
                    .replyMarkup(InlineKeyboardMarkup.builder().build())
                    .build();

            try {
                execute(editMarkup);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private InlineKeyboardMarkup createEditKeyboard() {
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(InlineKeyboardButton.builder().text("Изменить имя").callbackData("/edit_name").build());
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(InlineKeyboardButton.builder().text("Изменить фамилию").callbackData("/edit_surname").build());
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row3.add(InlineKeyboardButton.builder().text("Изменить отчество").callbackData("/edit_patronymic").build());
        List<InlineKeyboardButton> row4 = new ArrayList<>();
        row4.add(InlineKeyboardButton.builder().text("Изменить серию паспорта").callbackData("/edit_passport_series").build());
        List<InlineKeyboardButton> row5 = new ArrayList<>();
        row5.add(InlineKeyboardButton.builder().text("Изменить номер паспорта").callbackData("/edit_passport_number").build());
        List<InlineKeyboardButton> row6 = new ArrayList<>();
        row6.add(InlineKeyboardButton.builder().text("Изменить орган выдачи").callbackData("/edit_department").build());
        List<InlineKeyboardButton> row7 = new ArrayList<>();
        row7.add(InlineKeyboardButton.builder().text("Изменить код подразделения").callbackData("/edit_department_code").build());
        List<InlineKeyboardButton> row8 = new ArrayList<>();
        row8.add(InlineKeyboardButton.builder().text("Изменить дату выдачи паспорта").callbackData("/edit_passport_date").build());
        List<InlineKeyboardButton> row9 = new ArrayList<>();
        row9.add(InlineKeyboardButton.builder().text("Изменить телефон").callbackData("/edit_phone").build());
        List<InlineKeyboardButton> row10 = new ArrayList<>();
        row10.add(InlineKeyboardButton.builder().text("Изменить адрес").callbackData("/edit_address").build());
        List<InlineKeyboardButton> row11 = new ArrayList<>();
        row11.add(InlineKeyboardButton.builder().text("Изменить кадастрастровый номер").callbackData("/edit_cadastral").build());
        List<InlineKeyboardButton> row12 = new ArrayList<>();
        row12.add(InlineKeyboardButton.builder().text("Изменить дату начала проекта").callbackData("/edit_project_date").build());
        List<InlineKeyboardButton> row13 = new ArrayList<>();
        row13.add(InlineKeyboardButton.builder().text("Изменить первичный №").callbackData("/edit_primary_number").build());
        List<InlineKeyboardButton> row14 = new ArrayList<>();
        row14.add(InlineKeyboardButton.builder().text("Изменить вторичный №").callbackData("/edit_secondary_number").build());
        List<InlineKeyboardButton> row15 = new ArrayList<>();
        row15.add(InlineKeyboardButton.builder().text("Изменить цену").callbackData("/edit_price").build());
        List<InlineKeyboardButton> row16 = new ArrayList<>();
        row16.add(InlineKeyboardButton.builder().text("Изменить дату создания договора").callbackData("/edit_agreement_date").build());
        keyboard.add(row1);
        keyboard.add(row2);
        keyboard.add(row3);
        keyboard.add(row4);
        keyboard.add(row5);
        keyboard.add(row6);
        keyboard.add(row7);
        keyboard.add(row8);
        keyboard.add(row9);
        keyboard.add(row10);
        keyboard.add(row11);
        keyboard.add(row12);
        keyboard.add(row13);
        keyboard.add(row14);
        keyboard.add(row15);
        keyboard.add(row16);

        List<InlineKeyboardButton> rowConfirm = new ArrayList<>();
        rowConfirm.add(InlineKeyboardButton.builder()
                .text("❌ Отмена")
                .callbackData("/cancel")
                .build());
        rowConfirm.add(InlineKeyboardButton.builder()
                .text("✅ Подтвердить")
                .callbackData("/approve")
                .build());
        keyboard.add(rowConfirm);

        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    private BotState changeData(String callbackData, long chatId) {
        switch (callbackData) {
            case "/edit_name":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ИМЯ КЛИЕНТА.");
                return BotState.WAITING_CHANGE_NAME;
            case "/edit_surname":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ФАМИЛИЮ КЛИЕНТА.");
                return BotState.WAITING_CHANGE_SURNAME;
            case "/edit_patronymic":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ОТЧЕСТВО КЛИЕНТА.");
                return BotState.WAITING_CHANGE_PATRONYMIC;
            case "/edit_passport_series":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ СЕРИЮ ПАСПОРТА (4 цифры).");
                return BotState.WAITING_CHANGE_PASSPORT_SERIES;
            case "/edit_passport_number":
                sendMessageWithBackButton(chatId, "  ВВЕДИТЕ НОМЕР ПАСПОРТА (6 цифр).");
                return BotState.WAITING_CHANGE_PASSPORT_NUMBER;
            case "/edit_department":
                sendMessageWithBackButton(chatId, "  ВВЕДИТЕ МЕСТО ВЫДАЧИ ПАСПОРТА.");
                return BotState.WAITING_CHANGE_DEPARTMENT;
            case "/edit_department_code":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ КОД ДЕПАРТАМЕНТА (XXX-XXX).");
                return BotState.WAITING_CHANGE_DEPARTMENT_CODE;
            case "/edit_passport_date":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ДАТУ ВЫДАЧИ ПАСПОТА ДД-ММ-ГГГГ.");
                return BotState.WAITING_CHANGE_PASSPORT_DATE;
            case "/edit_phone":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ НОМЕР ТЕЛЕФОНА.");
                return BotState.WAITING_CHANGE_PHONE;
            case "/edit_address":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ АДРЕС ОБЪЕКТА.");
                return BotState.WAITING_CHANGE_ADDRESS;
            case "/edit_cadastral":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ КАДАСТРОВЫЙ НОМЕР.");
                return BotState.WAITING_CHANGE_CADASTRAL_NUMBER;
            case "/edit_project_date":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ДАТУ НАЧАЛА РАБОТ ДД-ММ-ГГГГ.");
                return BotState.WAITING_CHANGE_PROJECT_START_DATE;
            case "/edit_primary_number":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ПЕРВИЧНЫЙ НОМЕР ДОГОВОРА.");
                return BotState.WAITING_CHANGE_PRIMARY_NUMBER;
            case "/edit_secondary_number":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ВТОРИЧНЫЙ НОМЕР ДОГОВОРА.");
                return BotState.WAITING_CHANGE_SECONDARY_NUMBER;
            case "/edit_price":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ СТОИМОСТЬ.");
                return BotState.WAITING_CHANGE_PRICE;
            case "/edit_agreement_date":
                sendMessageWithBackButton(chatId, "ВВЕДИТЕ ДАТУ ЗАКЛЮЧЕНИЯ ДОГОВОРА ДД-ММ-ГГГГ.");
                return BotState.WAITING_CHANGE_AGREEMENT_DATE;
            default:
                return null;
        }
    }

    public static BotState getById(int id) {
        for (BotState state : BotState.values()) {
            if (state.getId() == id) {
                return state;
            }
        }
        return BotState.START;
    }
}
