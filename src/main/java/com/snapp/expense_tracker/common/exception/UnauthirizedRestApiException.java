package com.snapp.expense_tracker.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.modulith.NamedInterface;

@NamedInterface
public class UnauthirizedRestApiException extends RestApiException {
    public UnauthirizedRestApiException() {
        super(HttpStatus.UNAUTHORIZED.value());
    }
}
