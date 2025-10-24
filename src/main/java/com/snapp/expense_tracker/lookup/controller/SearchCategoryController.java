package com.snapp.expense_tracker.lookup.controller;

import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
import com.snapp.expense_tracker.lookup.service.LookupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Lookup Module")
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
