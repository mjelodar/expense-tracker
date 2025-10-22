package com.snapp.expense_tracker.notification.model;

import org.springframework.data.domain.Pageable;

public class GetNotificationRequest {
    private final Long userId;
    private final Pageable pageable;

    public GetNotificationRequest(Long userId, Pageable pageable) {
        this.userId = userId;
        this.pageable = pageable;
    }

    public Long getUserId() {
        return userId;
    }

    public Pageable getPageable() {
        return pageable;
    }
}
