package com.snapp.expense_tracker.cost.domain;

import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import com.snapp.expense_tracker.cost.ExpenseService;
import com.snapp.expense_tracker.cost.model.AddExpenseRequest;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ApplicationEventPublisher publisher;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository,
                              ApplicationEventPublisher publisher) {
        this.expenseRepository = expenseRepository;
        this.publisher = publisher;
    }

    @Override
    public void addExpense(AddExpenseRequest request) {
        Long userId = SecurityUtil.getUserId();
        Expense expense = new Expense();
        expense.setCategoryId(request.categoryId());
        expense.setCategoryName(request.categoryName());
        expense.setSubcategoryId(request.subCategoryId());
        expense.setSubcategoryName(request.subCategoryName());
        expense.setUserId(userId);
        expense.setCost(request.amount());
        expenseRepository.save(expense);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                publisher.publishEvent(new ExpenseAddedEvent(userId,
                        request.categoryId(),
                        request.categoryName(),
                        request.subCategoryId(),
                        request.subCategoryName(),
                        request.amount()));
            }
        });
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.findById(id).ifPresent(expense -> {
            if (expense.getUserId().equals(SecurityUtil.getUserId())) {
                expenseRepository.delete(expense);
            }
        });
    }
}
