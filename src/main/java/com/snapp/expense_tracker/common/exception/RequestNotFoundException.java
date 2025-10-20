package com.snapp.expense_tracker.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public class RequestNotFoundException extends RestApiException {
    public RequestNotFoundException() {
        super(HttpStatus.NOT_FOUND.value());
    }
}
