package com.snapp.expense_tracker.cost.controller;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.cost.model.AddExpenseRequest;
import com.snapp.expense_tracker.cost.model.ExpenseView;
import com.snapp.expense_tracker.cost.model.GetExpenseRequest;
import com.snapp.expense_tracker.cost.service.ExpenseService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Expense Module")
public class GetExpenseController {
    private final ExpenseService expenseService;

    public GetExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/api/cost/expense")
    public Page<ExpenseView> handle(@PageableDefault Pageable pageable){
        GetExpenseRequest request = new GetExpenseRequest(SecurityUtil.getUserId(),
                pageable);
        return expenseService.get(request);
    }
}
