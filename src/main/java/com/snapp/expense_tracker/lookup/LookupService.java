package com.snapp.expense_tracker.lookup;

import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;

import java.util.List;

public interface LookupService {
    List<SearchCategoryResponse> searchCategory(String categoryName);
}
