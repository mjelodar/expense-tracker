package com.snapp.expense_tracker.report.domain;

import com.snapp.expense_tracker.common.enums.NotificationType;
import com.snapp.expense_tracker.common.event.RuleMetEvent;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.report.model.AddRuleRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class RuleService {
    private final RuleRepository ruleRepository;
    private final ApplicationEventPublisher publisher;

    public RuleService(RuleRepository ruleRepository, ApplicationEventPublisher publisher) {
        this.ruleRepository = ruleRepository;
        this.publisher = publisher;
    }

    public void create(AddRuleRequest request) {
        Rule rule = new Rule();
        rule.setUserId(SecurityUtil.getUserId());
        rule.setCategoryId(request.categoryId());
        rule.setCategoryName(request.categoryName());
        rule.setSubcategoryId(request.subcategoryId());
        rule.setSubcategoryName(request.subcategoryName());
        rule.setNoOfRepeats(0L);
        rule.setCost(0d);
        rule.setDescription(request.description());
        rule.setTimeUnit(request.timeUnit());
        rule.setTimePeriod(request.timePeriod());
        rule.setThresholdCost(request.threshold());
        rule.setOperator(request.operator());
        updateExpirationDate(rule);
        ruleRepository.save(rule);
    }



    public void notifyExpiredRule(Rule rule) {
        if (rule.getCost() <= rule.getThresholdCost()) {
            sendNotification(rule);
            rule.setNotified(true);
        }
        updateExpirationDate(rule);
        ruleRepository.save(rule);
    }

    void sendNotification(Rule rule) {
        if (!rule.isNotified())
            switch (rule.getOperator()) {
                case GRATER_THAN, GREATER_EQUAL_THAN -> publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                        NotificationType.PASS_RULE_UNSUCCESSFULLY,
                        rule.getId(),
                        rule.getNoOfRepeats(),
                        rule.getCategoryName(),
                        rule.getSubcategoryName()));
                case LESS_THAN, LESS_EQUAL_THAN -> publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                        NotificationType.PASS_RULE_SUCCESSFULLY,
                        rule.getId(),
                        rule.getNoOfRepeats(),
                        rule.getCategoryName(),
                        rule.getSubcategoryName()));
            }
    }

    void updateExpirationDate(Rule rule) {
        switch (rule.getTimeUnit()) {
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

    boolean updateCost(Rule rule, Double currentCost, Double amount) {
        rule.setCost(currentCost + amount);
        if (rule.getCost() >= rule.getThresholdCost() && !rule.isNotified()) {
            rule.setNotified(true);
            return true;
        }
        return false;
    }
}
