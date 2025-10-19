package com.snapp.expense_tracker.user.domin;

public class UsernameAlreadyExistedException extends RuntimeException {
    public UsernameAlreadyExistedException() {
    }

    public UsernameAlreadyExistedException(String message) {
        super(message);
    }
}
