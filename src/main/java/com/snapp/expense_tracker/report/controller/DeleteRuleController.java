package com.snapp.expense_tracker.report.controller;

import com.snapp.expense_tracker.report.service.RuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Report Module")
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
