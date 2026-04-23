package com.softserve.academy.model;

import lombok.Getter;

@Getter
public enum PriorityCode {
    MIN_PRIORITY(1),
    MAX_PRIORITY(5),
    HIGH_PRIORITY(4);

    private final int priorityCode;

    PriorityCode(int priorityCode) {
        this.priorityCode = priorityCode;
    }

}
