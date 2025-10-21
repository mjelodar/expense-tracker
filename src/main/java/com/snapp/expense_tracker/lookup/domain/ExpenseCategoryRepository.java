package com.snapp.expense_tracker.lookup.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByCategoryContainingIgnoreCase(String category);
}
