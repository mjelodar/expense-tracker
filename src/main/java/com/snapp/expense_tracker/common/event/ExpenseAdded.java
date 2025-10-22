package com.snapp.expense_tracker.common.event;

public record ExpenseAdded(Long userId,
                           Long categoryId,
                           String categoryName,
                           Long subcategoryId,
                           String subcategoryName,
                           Double amount) {
}
