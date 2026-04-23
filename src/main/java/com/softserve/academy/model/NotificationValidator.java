package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;

public final class NotificationValidator {
    public static void validateBaseFields(String recipient, String message, int priority) {
        if (recipient == null || recipient.isBlank()) {
            throw new InvalidNotificationException("The recipient cannot be null or empty.");
        }

        if (message == null || message.isBlank()) {
            throw new InvalidNotificationException("The message cannot be empty.");
        }

        if (priority < PriorityCode.MIN_PRIORITY.getPriorityCode() || priority > PriorityCode.MAX_PRIORITY.getPriorityCode()) {
            StringBuilder sb = new StringBuilder("The priority must be between ");
            sb.append(PriorityCode.MIN_PRIORITY).append(" and ").append(PriorityCode.MAX_PRIORITY);
            throw new InvalidNotificationException(sb.toString());
        }
    }
}
