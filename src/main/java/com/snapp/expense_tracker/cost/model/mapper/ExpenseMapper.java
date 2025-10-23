package com.snapp.expense_tracker.cost.model.mapper;

import com.snapp.expense_tracker.cost.domain.Expense;
import com.snapp.expense_tracker.cost.model.ExpenseView;

import java.time.format.DateTimeFormatter;

public class ExpenseMapper {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static ExpenseView toExpenseView(Expense expense) {
        return new ExpenseView(expense.getCategoryName(),
                expense.getSubcategoryName(),
                expense.getCost().toString(),
                formatter.format(expense.getCreatedAt()));
    }
}
