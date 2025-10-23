package com.snapp.expense_tracker.cost.service;

import com.snapp.expense_tracker.cost.model.AddExpenseRequest;

public interface ExpenseService {
    void addExpense(AddExpenseRequest request);
    void deleteExpense(Long id);
}
