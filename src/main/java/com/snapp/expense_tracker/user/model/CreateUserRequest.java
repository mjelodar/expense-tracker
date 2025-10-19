package com.snapp.expense_tracker.user.model;

public record CreateUserRequest(String fullName, String email, String password) {
}
