package com.softserve.academy.model;

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
        // порожнє повідомлення (null) -> InvalidNotificationException
        // priority від 1 до 5, інакше -> InvalidNotificationException
        NotificationValidator.validateBaseFields(recipient, message, priority);

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

        return this.priority >= PriorityCode.HIGH_PRIORITY.getPriorityCode();
    }

    public void send() throws NotDeliverableException {
        // TODO: Шаблонний метод (Template Method):
        // 1. Перевірка isDeliverable()
        // 2. Якщо !isDeliverable() -> статус FAILED та throw NotDeliverableException
        // 3. performSend()
        // 4. Успіх -> статус SENT
        if (!isDeliverable()) {
            this.status = NotificationStatus.FAILED;
            throw new NotDeliverableException("The notification is not deliverable to: " + recipient);
        }

        try {
            performSend();
            this.status = NotificationStatus.SENT;
        } catch (NotDeliverableException exc) {
            this.status = NotificationStatus.FAILED;
            throw exc;
        }
    }

    protected abstract void performSend() throws NotDeliverableException;

    @Override
    public int compareTo(Notification other) {
        // TODO: Сортування за priority descending
        return Integer.compare(other.priority, this.priority);
    }

}
