package com.softserve.academy.model;

import lombok.Getter;

@Getter
public class SmsNotification extends Notification {
    private final String phoneNumber;
    private final boolean isFlash;

    private static final int MIN_DIGITS_AFTER_PLUS = 9;
    private static final int MAX_DIGITS_AFTER_PLUS = 14;
    private static final int MAX_MESSAGE_LENGTH = 160;

    public SmsNotification(String recipient, String message, int priority, String phoneNumber, boolean isFlash) {
        super(recipient, message, priority);

        this.phoneNumber = phoneNumber;
        this.isFlash = isFlash;
    }

    @Override
    public boolean isDeliverable() {
        String regex = String.format("^\\+\\d{%d,%d}$", MIN_DIGITS_AFTER_PLUS, MAX_DIGITS_AFTER_PLUS);

        return phoneNumber != null && phoneNumber.matches(regex);
    }

    public boolean isOverLimit() {
        return getMessage().length() > MAX_MESSAGE_LENGTH;
    }

    @Override
    public String getFormattedMessage() {
        return (getMessage().length() > MAX_MESSAGE_LENGTH) ? getMessage().substring(0, MAX_MESSAGE_LENGTH) : getMessage();
    }

    @Override
    public int estimateDeliverySeconds() {
        return DeliveryTimeConfig.SMS.getDelivery();
    }

    @Override
    protected void performSend() {
        StringBuilder sb = new StringBuilder("Sending to: ");
        sb.append(phoneNumber).append(" the message ").append(getFormattedMessage());
        System.out.println(sb);
    }

}
