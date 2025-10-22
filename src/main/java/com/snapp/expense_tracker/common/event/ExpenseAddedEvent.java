package com.snapp.expense_tracker.common.event;

import org.springframework.modulith.NamedInterface;

@NamedInterface
public record ExpenseAddedEvent(Long userId,
                                Long categoryId,
                                String categoryName,
                                Long subcategoryId,
                                String subcategoryName,
                                Double amount) {
}
