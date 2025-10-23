package com.snapp.expense_tracker.cost.service;

import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import com.snapp.expense_tracker.cost.domain.Expense;
import com.snapp.expense_tracker.cost.model.AddExpenseRequest;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.cost.model.ExpenseView;
import com.snapp.expense_tracker.cost.model.GetExpenseRequest;
import com.snapp.expense_tracker.cost.model.mapper.ExpenseMapper;
import com.snapp.expense_tracker.cost.repository.ExpenseRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Service
@Transactional
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final ApplicationEventPublisher publisher;

    public ExpenseService(ExpenseRepository expenseRepository,
                          ApplicationEventPublisher publisher) {
        this.expenseRepository = expenseRepository;
        this.publisher = publisher;
    }

    public void add(AddExpenseRequest request) {
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

    public void delete(Long id) {
        expenseRepository.findById(id).ifPresent(expense -> {
            if (expense.getUserId().equals(SecurityUtil.getUserId())) {
                expenseRepository.delete(expense);
            }
        });
    }

    public Page<ExpenseView> get(GetExpenseRequest request){
        return expenseRepository.findByUserIdOrderByCreatedAtDesc(request.getUserId(), request.getPageable()).
                map(ExpenseMapper::toExpenseView);
    }
}
