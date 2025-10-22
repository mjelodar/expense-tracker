package com.snapp.expense_tracker.report.model;

import com.snapp.expense_tracker.common.enums.Operator;
import com.snapp.expense_tracker.common.enums.TimeUnit;

public record AddRuleRequest(String description,
                             Long categoryId,
                             String categoryName,
                             Long subcategoryId,
                             String subcategoryName,
                             TimeUnit timeUnit,
                             Integer timePeriod,
                             Operator operator,
                             double threshold) {
}
