package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

@Getter
public class PushNotification extends Notification {
    private final String deviceToken;
    private final String iconUrl;

    private static final int MIN_DEVICE_TOKEN_LENGTH = 10;
    private static final int DEFAULT_DELIVERY_TIME_SECONDS = 1;

    public PushNotification(String recipient, String message, int priority, String deviceToken, String iconUrl) {
        super(recipient, message, priority);
        // TODO: Ініціалізація додаткових полів
        this.deviceToken = deviceToken;
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean isDeliverable() {
        // TODO: deviceToken не blank і довжина > 10
        // MIN_DEVICE_TOKEN_LENGTH will need to be moved to a separate enum
        return !deviceToken.isBlank() && deviceToken.length() >= MIN_DEVICE_TOKEN_LENGTH;
    }

    public boolean isSilent() {
        // TODO: true якщо message порожнє (тільки тайтл)
        return message == null || message.isBlank();
    }

    @Override
    public String getFormattedMessage() {
        // TODO: 🔔 {message} (якщо silent -> 🔔 (silent))
        return isSilent() ? "🔔 (silent)" : "🔔 " + message;
    }

    @Override
    public int estimateDeliverySeconds() {
        // TODO: 1
        //DEFAULT_DELIVERY_TIME_SECONDS will need to be moved to a separate enum
        return DEFAULT_DELIVERY_TIME_SECONDS;
    }

    @Override
    protected void performSend() {
        // TODO: Симуляція відправки (println)
        StringBuilder sb = new StringBuilder("The push notification sent to token: ");
        sb.append(deviceToken).append(" with icon: ").append(iconUrl).append(". Message: ").append(getFormattedMessage());
        System.out.println(sb);
    }

}
