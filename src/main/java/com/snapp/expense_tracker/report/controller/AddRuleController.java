package com.snapp.expense_tracker.report.controller;

import com.snapp.expense_tracker.report.service.RuleService;
import com.snapp.expense_tracker.report.model.AddRuleRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddRuleController {
    private final RuleService ruleService;

    public AddRuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @PostMapping("/api/report/rule")
    public void handle(@RequestBody AddRuleRequest request){
        ruleService.create(request);
    }
}
