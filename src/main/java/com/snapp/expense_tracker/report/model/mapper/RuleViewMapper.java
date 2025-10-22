package com.snapp.expense_tracker.report.model.mapper;

import com.snapp.expense_tracker.report.domain.Rule;
import com.snapp.expense_tracker.report.model.RuleView;

public class RuleViewMapper {
    public static RuleView toView(Rule rule) {
        RuleView ruleView = new RuleView(rule.getDescription(),
                rule.getCategoryName(),
                rule.getSubcategoryName(),
                rule.getTimeUnit(),
                rule.getTimePeriod(),
                rule.getCost(),
                rule.getThresholdCost(),
                rule.getOperator());
    }
}
