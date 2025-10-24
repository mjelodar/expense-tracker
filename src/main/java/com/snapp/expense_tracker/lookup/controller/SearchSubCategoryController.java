package com.snapp.expense_tracker.lookup.controller;

import com.snapp.expense_tracker.lookup.model.SearchSubCategoryResponse;
import com.snapp.expense_tracker.lookup.service.LookupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Tag(name = "Lookup Module")
public class SearchSubCategoryController {
    private final LookupService lookupService;

    public SearchSubCategoryController(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    @GetMapping("/api/lookup/subcategory/search/{categoryId}")
    public List<SearchSubCategoryResponse> handle(@PathVariable Long categoryId,
                                                  @RequestParam(required = false) String text) {
        return lookupService.searchSubCategory(categoryId, text);
    }
}
