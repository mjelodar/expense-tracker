package com.snapp.expense_tracker.lookup.controller;

import com.snapp.expense_tracker.lookup.LookupService;
import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
import com.snapp.expense_tracker.lookup.model.SearchSubCategoryResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchSubCategoryController {
    private final LookupService lookupService;

    public SearchSubCategoryController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/api/lookup/subcategory/search/{categoryId}")
    public List<SearchSubCategoryResponse> handle(@PathVariable Long categoryId,
                                                  @RequestParam String text) {
        return lookupService.searchSubCategory(categoryId, text);
    }
}
