package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

@Component
public class ExpenseAddedListener {
    private final RuleRepository ruleRepository;

    public ExpenseAddedListener(RuleRepository ruleRepository) {
        this.ruleRepository = ruleRepository;
    }
    @DomainEventHandler
    public void onExpenseAdded(ExpenseAddedEvent event) {
        Rule rule = ruleRepository.findByUserIdAndCategoryIdAndSubcategoryId(event.userId(),
                event.categoryId(),
                event.subcategoryId()).orElseThrow(RuleNotFoundException::new);

        if (rule.getExpirationAt().isAfter(LocalDateTime.now())){
            updateExpirationDate(rule);
            updateCost(rule, 0d, event.amount());
        }else {
            updateCost(rule, rule.getCost(), event.amount());
        }
        ruleRepository.save(rule);
        if (rule.getCost() >)
    }

    private void updateExpirationDate(Rule rule) {
        switch (rule.getTimeUnit()){
            case DAY -> {
                while (rule.getExpirationAt().isBefore(LocalDateTime.now())) {
                    rule.setExpirationAt(rule.getExpirationAt().plusDays(rule.getTimePeriod()));
                }
            }
            case WEEK -> {
                while (rule.getExpirationAt().isBefore(LocalDateTime.now())) {
                    rule.setExpirationAt(rule.getExpirationAt().plusWeeks(rule.getTimePeriod()));
                }
            }
            case MONTH -> {
                while (rule.getExpirationAt().isBefore(LocalDateTime.now())) {
                    rule.setExpirationAt(rule.getExpirationAt().plusMonths(rule.getTimePeriod()));
                }
            }
            case YEAR -> {
                while (rule.getExpirationAt().isBefore(LocalDateTime.now())) {
                    rule.setExpirationAt(rule.getExpirationAt().plusYears(rule.getTimePeriod()));
                }
            }
        }
    }

    private void updateCost(Rule rule, Double currentCost, Double amount) {
        rule.setCost(currentCost + amount);
    }
}
