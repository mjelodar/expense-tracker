package com.snapp.expense_tracker.notification.model;

import com.snapp.expense_tracker.common.enums.NotificationType;

public record NotificationView(Long id,
                               NotificationType type,
                               String message,
                               boolean seen) {
}
