package com.snapp.expense_tracker.cost.model;

public record ExpenseView(String categoryName,
                          String subcategoryName,
                          String amount,
                          String date) {
}
