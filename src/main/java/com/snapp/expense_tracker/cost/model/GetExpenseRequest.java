package com.snapp.expense_tracker.cost.model;

import org.springframework.data.domain.Pageable;

public class GetExpenseRequest {
    private final Long userId;
    private final Pageable pageable;

    public GetExpenseRequest(Long userId, Pageable pageable) {
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
