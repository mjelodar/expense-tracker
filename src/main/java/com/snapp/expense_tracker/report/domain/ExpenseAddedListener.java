package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.enums.NotificationType;
import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import com.snapp.expense_tracker.common.event.RuleMetEvent;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;

@Component
public class ExpenseAddedListener {
    private final RuleService ruleService;
    private final RuleRepository ruleRepository;

    public ExpenseAddedListener(RuleService ruleService, RuleRepository ruleRepository) {
        this.ruleService = ruleService;
        this.ruleRepository = ruleRepository;
    }

    @DomainEventHandler
    public void onExpenseAdded(ExpenseAddedEvent event) {
        Rule rule = ruleRepository.findByUserIdAndCategoryIdAndSubcategoryId(event.userId(),
                event.categoryId(),
                event.subcategoryId()).orElseThrow(RuleNotFoundException::new);
        boolean isThresholdMet;
        if (rule.getExpirationAt().isAfter(LocalDateTime.now())){
            ruleService.updateExpirationDate(rule);
            isThresholdMet = updateCost(rule, 0d, event.amount());
        }else {
            isThresholdMet = updateCost(rule, rule.getCost(), event.amount());
        }
        ruleRepository.save(rule);
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void afterCommit() {
                if (isThresholdMet)
                    ruleService.sendNotification(rule);
            }
        });
    }

    private boolean updateCost(Rule rule, Double currentCost, Double amount) {
        rule.setCost(currentCost + amount);
        if (rule.getCost() >= rule.getThresholdCost() && !rule.isNotified()){
            rule.setNotified(true);
            return true;
        }
        return false;
    }


}
