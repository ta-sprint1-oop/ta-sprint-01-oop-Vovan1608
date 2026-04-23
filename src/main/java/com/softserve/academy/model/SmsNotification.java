package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

@Getter
public class SmsNotification extends Notification {
    private final String phoneNumber;
    private final boolean isFlash;

    private static final int MIN_PHONE_LENGTH = 9;
    private static final int MAX_PHONE_LENGTH = 14;
    private static final int MAX_MESSAGE_LENGTH = 160;
    private static final int DEFAULT_DELIVERY_TIME_SECONDS = 5;


    public SmsNotification(String recipient, String message, int priority, String phoneNumber, boolean isFlash) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.phoneNumber = phoneNumber;
        this.isFlash = isFlash;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Номер починається з + і має довжину 10-15 символів
        String regex = String.format("^\\+\\d{%d,%d}$", MIN_PHONE_LENGTH, MAX_PHONE_LENGTH);

        return phoneNumber != null && phoneNumber.matches(regex);
    }

    public boolean isOverLimit() {
        // TODO: true якщо message > 160 символів
        return getMessage().length() > MAX_MESSAGE_LENGTH;
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Обрізає до 160 символів якщо довше
        return (getMessage().length() > MAX_MESSAGE_LENGTH) ? getMessage().substring(0, MAX_MESSAGE_LENGTH) : getMessage();
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 5
        return DEFAULT_DELIVERY_TIME_SECONDS;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        StringBuilder sb = new StringBuilder("Sending to: ");
        sb.append(phoneNumber).append(" the message ").append(getMessage());
        System.out.println(sb);
    }

}
