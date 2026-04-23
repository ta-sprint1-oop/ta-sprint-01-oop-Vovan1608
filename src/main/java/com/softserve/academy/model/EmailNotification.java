package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

import java.util.List;

@Getter
public class EmailNotification extends Notification {
    private final String senderEmail;
    private final String subject;
    private final boolean hasAttachment;

    private static final int DEFAULT_DELIVERY_TIME_SECONDS = 30;

    public EmailNotification(String recipient, String message, int priority, String senderEmail, String subject, boolean hasAttachment) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.senderEmail = senderEmail;
        this.subject = subject;
        this.hasAttachment = hasAttachment;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: Перевірка що email містить @ і .
        return getRecipient().contains("@") && getRecipient().contains(".");
    }

    public boolean isSpam() {
        // TODO: Якщо subject містить "free", "win", "click" (case insensitive)
        //If we have 10 words? May be Stream...
        return subject.matches("(?i).*(free|win|click).*");
    }

    @Override
    public String getFormattedMessage() {
        // TODO: Subject: {subject}\n{message}
        return String.format("Subject: %s\n%s", subject, getMessage());
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 30
        // DEFAULT_DELIVERY_TIME_SECONDS will need to be moved to a separate enum
        return DEFAULT_DELIVERY_TIME_SECONDS;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        StringBuilder sb = new StringBuilder("Sending Email Notification from ");
        sb.append(senderEmail).append("to ").append(getRecipient());
        System.out.println(sb);
    }

}
