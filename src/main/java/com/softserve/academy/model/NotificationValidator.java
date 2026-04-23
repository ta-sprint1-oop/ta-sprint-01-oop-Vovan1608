package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;

public final class NotificationValidator {
    public static void validateRecipientIsNotNull(String recipient) {
        if (recipient == null) {
            throw new InvalidNotificationException("The recipient cannot be null.");
        }
    }

    public static void validateRecipientIsNotEmpty(String recipient) {
        if (recipient.isBlank()) {
            throw new InvalidNotificationException("The recipient cannot be empty.");
        }
    }

    public static void validateMessageIsNotNull(String message) {
        if (message == null) {
            throw new InvalidNotificationException("The message cannot be empty.");
        }
    }

    public static void validatePriorityCodeMustBeBetweenMinAndMax(int priority) {
       if (priority < PriorityCode.MIN_PRIORITY.getPriorityCode() || priority > PriorityCode.MAX_PRIORITY.getPriorityCode()) {
            StringBuilder sb = new StringBuilder("The priority must be between ");
            sb.append(PriorityCode.MIN_PRIORITY).append(" and ").append(PriorityCode.MAX_PRIORITY);
            throw new InvalidNotificationException(sb.toString());
        }
    }

    public static void validateBaseFields(String recipient, String message, int priority) {
        validateRecipientIsNotNull(recipient);
        validateRecipientIsNotEmpty(recipient);
        validateMessageIsNotNull(message);
        validatePriorityCodeMustBeBetweenMinAndMax(priority);
    }
}
