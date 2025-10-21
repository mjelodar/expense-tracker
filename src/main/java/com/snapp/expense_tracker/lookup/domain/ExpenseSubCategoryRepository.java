package com.snapp.expense_tracker.lookup.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseSubCategoryRepository extends JpaRepository<ExpenseSubCategory, Long> {
    List<ExpenseSubCategory> findByCategoryIdAndSubCategoryContainingIgnoreCase(Long categoryId, String subCategory);

    List<ExpenseSubCategory> findByCategoryId(Long categoryId);
}
