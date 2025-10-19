package com.snapp.expense_tracker.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public class BadRequestException extends RestApiException {
    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST.value());
    }
}
