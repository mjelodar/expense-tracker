package com.snapp.expense_tracker.report.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByUserIdAndCategoryIdAndSubcategoryId(Long userId, Long categoryId, Long subcategoryId);
}
