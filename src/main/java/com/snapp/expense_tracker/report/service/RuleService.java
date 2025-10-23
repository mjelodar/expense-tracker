package com.snapp.expense_tracker.report.service;

import com.snapp.expense_tracker.common.enums.NotificationType;
import com.snapp.expense_tracker.common.event.RuleMetEvent;
import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.report.domain.Rule;
import com.snapp.expense_tracker.report.exception.RuleNotFoundException;
import com.snapp.expense_tracker.report.repository.RuleRepository;
import com.snapp.expense_tracker.report.model.AddRuleRequest;
import com.snapp.expense_tracker.report.model.GetRuleRequest;
import com.snapp.expense_tracker.report.model.RuleView;
import com.snapp.expense_tracker.report.model.mapper.RuleViewMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
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
        rule.setExpirationAt(LocalDateTime.now());
        updateExpirationDate(rule);
        ruleRepository.save(rule);
    }

    public void delete(Long id){
        ruleRepository.findById(id).ifPresent(rule -> {
            if (!rule.getUserId().equals(SecurityUtil.getUserId())) {
                ruleRepository.deleteById(id);
            }
        });
    }

    public Page<RuleView> get(GetRuleRequest request) {
        return ruleRepository.findByUserId(request.getUserId(), request.getPageable()).
                map(RuleViewMapper::toView);
    }

    public void notifyExpiredRule(Rule rule) {
        if (rule.getCost() <= rule.getThresholdCost()) {
            sendNotification(rule);
            rule.setNotified(true);
        }
        updateExpirationDate(rule);
        ruleRepository.save(rule);
    }

    public void sendNotification(Rule rule) {
        if (!rule.isNotified())
            switch (rule.getOperator()) {
                case GREATER_THAN, GREATER_EQUAL_THAN -> publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                        NotificationType.PASS_RULE_UNSUCCESSFULLY,
                        rule.getId(),
                        rule.getDescription(),
                        rule.getCategoryName(),
                        rule.getSubcategoryName()));
                case LESS_THAN, LESS_EQUAL_THAN -> publisher.publishEvent(new RuleMetEvent(rule.getUserId(),
                        NotificationType.PASS_RULE_SUCCESSFULLY,
                        rule.getId(),
                        rule.getDescription(),
                        rule.getCategoryName(),
                        rule.getSubcategoryName()));
            }
    }

    public void updateExpirationDate(Rule rule) {
        rule = ruleRepository.findById(rule.getId()).orElseThrow(RuleNotFoundException::new);
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
        ruleRepository.save(rule);
    }

    public boolean updateCost(Rule rule, Double currentCost, Double amount) {
        rule = ruleRepository.findById(rule.getId()).orElseThrow(RuleNotFoundException::new);
        rule.setCost(currentCost + amount);
        ruleRepository.save(rule);
        return rule.getCost() >= rule.getThresholdCost();
    }
}
