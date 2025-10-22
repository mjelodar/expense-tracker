package com.snapp.expense_tracker.report.model;

import org.springframework.data.domain.Pageable;

public class GetRuleRequest {
    private Long userId;
    private Pageable pageable;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
