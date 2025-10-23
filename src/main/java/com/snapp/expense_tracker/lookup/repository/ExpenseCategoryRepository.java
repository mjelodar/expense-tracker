package com.snapp.expense_tracker.lookup.repository;

import com.snapp.expense_tracker.lookup.domain.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
    List<ExpenseCategory> findByCategoryContainingIgnoreCase(String category);
}
