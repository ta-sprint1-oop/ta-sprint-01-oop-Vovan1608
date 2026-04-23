package com.softserve.academy.model;

import com.softserve.academy.exception.NotDeliverableException;
import lombok.Getter;

@Getter
public class PushNotification extends Notification {
    private final String deviceToken;
    private final String iconUrl;

    private static final int MINIMUM_TOKEN_LENGTH = 10;

    public PushNotification(String recipient, String message, int priority, String deviceToken, String iconUrl) {
        super(recipient, message, priority);

        this.deviceToken = deviceToken;
        this.iconUrl = iconUrl;
    }

    @Override
    public boolean isDeliverable() {
        return deviceToken != null && !deviceToken.isBlank() && deviceToken.length() > MINIMUM_TOKEN_LENGTH;
    }

    public boolean isSilent() {
        return getMessage().isBlank();
    }

    @Override
    public String getFormattedMessage() {
        return isSilent() ? "🔔 (silent)" : "🔔 " + getMessage();
    }

    @Override
    public int estimateDeliverySeconds() {
        return DeliveryTimeConfig.PUSH.getDelivery();
    }

    @Override
    protected void performSend() {
        StringBuilder sb = new StringBuilder("The push notification sent to token: ");
        sb.append(deviceToken).append(" with icon: ").append(iconUrl).append(". Message: ").append(getFormattedMessage());
        System.out.println(sb);
    }

}
