package com.snapp.expense_tracker.report.controller;

import com.snapp.expense_tracker.report.service.RuleService;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeleteRuleController {
    private final RuleService ruleService;

    public DeleteRuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @DeleteMapping("/api/report/rule/{id}")
    public void handle(@PathVariable Long id){
        ruleService.delete(id);
    }
}
