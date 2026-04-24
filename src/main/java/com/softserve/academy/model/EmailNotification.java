package com.softserve.academy.model;

import lombok.Getter;

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
        return getSubject() != null && getSubject().matches("(?i).*(free|win|click).*");
    }

    @Override
    public String getFormattedMessage() {
        return String.format("Subject: %s\n%s", getSubject(), getMessage());
    }

    @Override
    public int estimateDeliverySeconds() {
        return DeliveryTimeConfig.EMAIL.getDelivery();
    }

    @Override
    protected void performSend() {
        StringBuilder sb = new StringBuilder("Sending Email Notification from ");
        sb.append(getSenderEmail()).append(" to ").append(getRecipient());
        System.out.println(sb);
    }

}
