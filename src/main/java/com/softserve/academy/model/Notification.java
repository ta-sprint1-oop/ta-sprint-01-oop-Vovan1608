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
        return this.priority >= PriorityCode.HIGH_PRIORITY.getPriorityCode();
    }

    public void send() throws NotDeliverableException {
        if (!isDeliverable()) {
            this.status = NotificationStatus.FAILED;
            throw new NotDeliverableException("The notification is not deliverable to: " + getRecipient());
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
        return Integer.compare(other.priority, this.priority);
    }

}
