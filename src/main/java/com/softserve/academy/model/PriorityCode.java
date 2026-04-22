package com.softserve.academy.model;

import lombok.Getter;

@Getter
public enum PriorityCode {
    MIN(1),
    MAX(5);

    private final int priorityCode;

    PriorityCode(int priorityCode) {
        this.priorityCode = priorityCode;
    }

}
