package com.softserve.academy.model;

import lombok.Getter;

@Getter
public enum DeliveryTimeConfig {
    EMAIL(30),
    SMS(5),
    PUSH(1);

    private final int delivery;

    DeliveryTimeConfig(int delivery) {
        this.delivery = delivery;
    }
}
