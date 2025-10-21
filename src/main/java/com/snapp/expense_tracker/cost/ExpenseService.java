package com.snapp.expense_tracker.cost;

import com.snapp.expense_tracker.cost.model.AddExpenseRequest;

public interface ExpenseService {
    void addExpense(AddExpenseRequest request);
}
