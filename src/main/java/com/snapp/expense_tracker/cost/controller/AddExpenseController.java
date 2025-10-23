package com.snapp.expense_tracker.cost.controller;

import com.snapp.expense_tracker.cost.service.ExpenseService;
import com.snapp.expense_tracker.cost.model.AddExpenseRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddExpenseController {
    private final ExpenseService expenseService;

    public AddExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping("/api/cost/expense")
    public void handle(@RequestBody AddExpenseRequest request){
        expenseService.addExpense(request);
    }
}
