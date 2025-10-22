package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.enums.TimeUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RuleRepository extends JpaRepository<Rule, Long> {
    Optional<Rule> findByUserIdAndCategoryIdAndSubcategoryId(Long userId, Long categoryId, Long subcategoryId);

    List<Rule> findByTimeUnitAndExpirationAtBefore(TimeUnit time, LocalDateTime expirationAt);

    Page<Rule> findByUserId(Long userId, Pageable pageable);
}
