package com.snapp.expense_tracker.user.model;

public record RefreshRequest(Long userId, String refreshToken) {
}
