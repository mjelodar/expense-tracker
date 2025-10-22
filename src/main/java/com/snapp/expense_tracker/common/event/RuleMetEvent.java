package com.snapp.expense_tracker.common.event;

import com.snapp.expense_tracker.common.enums.NotificationType;

public record RuleMetEvent(Long userId,
                           NotificationType notificationType,
                           Long ruleId,
                           String description,
                           String categoryName,
                           String subcategoryName) {}


