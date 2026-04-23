package com.softserve.academy.model;

import com.softserve.academy.exception.InvalidNotificationException;

public final class PhoneNumberValidator {
    public static void validatePhoneNumberIsNotEmpty(String phonNumber) {
        if (phonNumber.isBlank()) {
            throw new InvalidNotificationException("The phone number cannot be empty.");
        }
    }

    public static void validatePhoneNumberIsNotNull(String phonNumber) {
        if (phonNumber == null) {
            throw new InvalidNotificationException("The phone number cannot be null.");
        }
    }

    public static void validatePhoneNumber(String phoneNumber) {
        validatePhoneNumberIsNotNull(phoneNumber);
        validatePhoneNumberIsNotEmpty(phoneNumber);
    }

}
