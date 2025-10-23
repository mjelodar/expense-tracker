package com.snapp.expense_tracker.cost.repository;

import com.snapp.expense_tracker.cost.domain.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
