package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

import java.util.List;

@Getter
public class EmailNotification extends Notification {
    private final String senderEmail;
    private final String subject;
    private final boolean hasAttachment;

    public EmailNotification(String recipient, String message, int priority, String senderEmail, String subject, boolean hasAttachment) {
        super(recipient, message, priority);

        this.senderEmail = senderEmail;
        this.subject = subject;
        this.hasAttachment = hasAttachment;
    }

    @Override
    public boolean isDeliverable() {
        return getRecipient().matches("^[^@]+@[^@]+\\.[^@]+$");
    }

    public boolean isSpam() {
        return subject.matches("(?i).*(free|win|click).*");
    }

    @Override
    public String getFormattedMessage() {
        return String.format("Subject: %s\n%s", subject, getMessage());
    }

    @Override
    public int estimateDeliverySeconds() {
        return DeliveryTimeConfig.EMAIL.getDelivery();
    }

    @Override
    protected void performSend() {
        StringBuilder sb = new StringBuilder("Sending Email Notification from ");
        sb.append(senderEmail).append(" to ").append(getRecipient());
        System.out.println(sb);
    }

}
