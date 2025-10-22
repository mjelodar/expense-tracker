package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;

@Component
public class ExpenseAddedListener {
    private final RuleRepository ruleRepository;
    private final ApplicationEventPublisher publisher;

    public ExpenseAddedListener(RuleRepository ruleRepository,
                                ApplicationEventPublisher publisher) {
        this.ruleRepository = ruleRepository;
        this.publisher = publisher;
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
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                sendNotification(rule);
            }
        });
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

    private void sendNotification(Rule rule) {
        switch (rule.getOperator()){
            case GRATER_THAN -> {
                if (rule.getCost() > rule.getThresholdCost())
                    publisher.publishEvent();
            }
        }
    }
}
