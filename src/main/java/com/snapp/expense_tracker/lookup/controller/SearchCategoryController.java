package com.snapp.expense_tracker.lookup.controller;

import com.snapp.expense_tracker.lookup.LookupService;
import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchCategoryController {
    private final LookupService lookupService;

    public SearchCategoryController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/api/lookup/category/search")
    public List<SearchCategoryResponse> handle(@RequestParam String text) {
        return lookupService.searchCategory(text);
    }
}
