package com.snapp.expense_tracker.common.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenRestApiException extends RestApiException {
    public ForbiddenRestApiException() {
        super(HttpStatus.FORBIDDEN.value());
    }
}
