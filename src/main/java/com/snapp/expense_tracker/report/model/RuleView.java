package com.snapp.expense_tracker.report.model;

import com.snapp.expense_tracker.common.enums.Operator;
import com.snapp.expense_tracker.common.enums.TimeUnit;

public record RuleView(String description,
                       String categoryName,
                       String subcategoryName,
                       TimeUnit timeUnit,
                       Integer timePeriod,
                       double cost,
                       double threshold,
                       Operator operator) {
}
