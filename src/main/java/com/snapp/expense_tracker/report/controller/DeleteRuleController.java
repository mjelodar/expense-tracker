package com.snapp.expense_tracker.report.controller;

import com.snapp.expense_tracker.report.domain.RuleService;
import com.snapp.expense_tracker.report.model.AddRuleRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteRuleController {
    private final RuleService ruleService;

    public DeleteRuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/api/report/rule/{id}")
    public void handle(@PathVariable Long id){
        ruleService.create(request);
    }
}
