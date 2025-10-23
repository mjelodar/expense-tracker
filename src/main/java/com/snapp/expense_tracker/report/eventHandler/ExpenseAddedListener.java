package com.snapp.expense_tracker.report.eventHandler;

import com.snapp.expense_tracker.common.event.ExpenseAddedEvent;
import com.snapp.expense_tracker.report.domain.Rule;
import com.snapp.expense_tracker.report.exception.RuleNotFoundException;
import com.snapp.expense_tracker.report.repository.RuleRepository;
import com.snapp.expense_tracker.report.service.RuleService;
import org.jmolecules.event.annotation.DomainEventHandler;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
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

    @EventListener
    @Transactional
    @Async
    public void onExpenseAdded(ExpenseAddedEvent event) {
        Rule rule = ruleRepository.findByUserIdAndCategoryIdAndSubcategoryId(event.userId(),
                event.categoryId(),
                event.subcategoryId()).orElseThrow(RuleNotFoundException::new);
        boolean isThresholdMet;
        if (rule.getExpirationAt().isBefore(LocalDateTime.now())){
            ruleService.updateExpirationDate(rule);
            isThresholdMet = ruleService.updateCost(rule, 0d, event.amount());
        }else {
            isThresholdMet = ruleService.updateCost(rule, rule.getCost(), event.amount());
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

}
