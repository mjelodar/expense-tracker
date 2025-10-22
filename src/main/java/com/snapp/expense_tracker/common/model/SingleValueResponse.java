package com.snapp.expense_tracker.common.model;

import org.springframework.modulith.NamedInterface;

@NamedInterface
public class SingleValueResponse<T> {
    T value;

    public SingleValueResponse(T value) {
        this.value = value;
    }
}
