package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;
import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

@Getter
public abstract class Notification implements Comparable<Notification> {
    // Getters
    protected String recipient;
    protected String message;
    protected int priority;
    protected NotificationStatus status;

    public Notification(String recipient, String message, int priority) {
        // TODO: Базова валідація в конструкторі:
        // порожній отримувач -> InvalidNotificationException
        if (recipient == null || recipient.isBlank()) {
            throw new InvalidNotificationException("Recipient cannot be null or empty.");
        }
        // порожнє повідомлення (null) -> InvalidNotificationException
        if (message == null || message.isBlank()) {
            throw new InvalidNotificationException("Message cannot be empty.");
        }
        // priority від 1 до 5, інакше -> InvalidNotificationException
        if (priority < PriorityCode.MIN.getPriorityCode() || priority > PriorityCode.MAX.getPriorityCode()) {
            throw new InvalidNotificationException("Priority cannot be less then 1 or more than 5.");
        }

        this.recipient = recipient;
        this.message = message;
        this.priority = priority;
        this.status = NotificationStatus.PENDING;
    }

    public abstract boolean isDeliverable();

    public abstract String getFormattedMessage();

    public abstract int estimateDeliverySeconds();

    public boolean isHighPriority() {
        // TODO: Пріоритет >= 4

        return false;
    }

    public void send() throws NotDeliverableException {
        // TODO: Шаблонний метод (Template Method):
        // 1. Перевірка isDeliverable()
        // 2. Якщо !isDeliverable() -> статус FAILED та throw NotDeliverableException
        // 3. performSend()
        // 4. Успіх -> статус SENT
    }

    protected abstract void performSend() throws NotDeliverableException;

    @Override
    public int compareTo(Notification other) {
        // TODO: Сортування за priority descending
        return 0;
    }

}
