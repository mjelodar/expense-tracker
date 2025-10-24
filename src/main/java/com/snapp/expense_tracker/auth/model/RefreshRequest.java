package com.snapp.expense_tracker.auth.model;

public record RefreshRequest(Long userId, String refreshToken) {
}
