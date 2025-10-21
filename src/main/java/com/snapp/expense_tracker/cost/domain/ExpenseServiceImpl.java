package com.snapp.expense_tracker.cost.domain;

import com.snapp.expense_tracker.cost.ExpenseService;
import com.snapp.expense_tracker.cost.model.AddExpenseRequest;
import com.snapp.expense_tracker.cost.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public void addExpense(AddExpenseRequest request) {
        Expense expense = new Expense();
        expense.setCategoryId(request.categoryId());
        expense.setCategoryName(request.categoryName());
        expense.setSubcategoryId(request.subCategoryId());
        expense.setSubcategoryName(request.subCategoryName());
        expense.setUserId(SecurityUtil.getUserId());
        expenseRepository.save(expense);
    }
}
