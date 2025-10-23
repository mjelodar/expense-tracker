package com.snapp.expense_tracker.cost.controller;

import com.snapp.expense_tracker.cost.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteExpenseController {
    private final ExpenseService expenseService;

    public DeleteExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @DeleteMapping("/api/cost/expense/{id}")
    public void handle(@PathVariable Long id){
        expenseService.deleteExpense(id);
    }
}
