package com.snapp.expense_tracker.auth.model;

public record LoginResponse(String accessToken, String refreshToken, Long userId) {
}
