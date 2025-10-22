package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.enums.NotificationType;
import com.snapp.expense_tracker.common.event.RuleMetEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class RuleService {
    private final RuleRepository ruleRepository;
    private final ApplicationEventPublisher publisher;

    public RuleService(RuleRepository ruleRepository,
                       ApplicationEventPublisher publisher) {
        this.ruleRepository = ruleRepository;
        this.publisher = publisher;
    }

    public void notifyExpiredRule(Rule rule) {
        if (rule.getCost() <= rule.getThresholdCost()) {
            sendNotification(rule);
        }
        updateExpirationDate(rule);
    }

    void sendNotification(Rule rule) {
        if (!rule.isNotified())
            switch (rule.getOperator()){
                case GRATER_THAN, GREATER_EQUAL_THAN -> publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                        NotificationType.PASS_RULE_UNSUCCESSFULLY,
                        rule.getId(),
                        rule.getNoOfRepeats(),
                        rule.getCategoryName(),
                        rule.getSubcategoryName()));
                case LESS_THAN, LESS_EQUAL_THAN -> {
                    if (rule.getCost() <= rule.getThresholdCost())
                        publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                                NotificationType.PASS_RULE_SUCCESSFULLY,
                                rule.getId(),
                                rule.getNoOfRepeats(),
                                rule.getCategoryName(),
                                rule.getSubcategoryName()));
                }
            }
    }

    void updateExpirationDate(Rule rule) {
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
}
