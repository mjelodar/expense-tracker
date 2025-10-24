package com.snapp.expense_tracker.report.controller;

import com.snapp.expense_tracker.common.util.SecurityUtil;
import com.snapp.expense_tracker.report.service.RuleService;
import com.snapp.expense_tracker.report.model.GetRuleRequest;
import com.snapp.expense_tracker.report.model.RuleView;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Report Module")
public class GetRuleController {
    private final RuleService ruleService;

    public GetRuleController(RuleService ruleService) {
        this.ruleService = ruleService;
    }

    @GetMapping("/api/report/rule")
    public Page<RuleView> handle(@PageableDefault Pageable pageable){
        GetRuleRequest request = new GetRuleRequest();
        request.setUserId(SecurityUtil.getUserId());
        request.setPageable(pageable);
        return ruleService.get(request);
    }
}
