package com.snapp.expense_tracker.lookup;

import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
import com.snapp.expense_tracker.lookup.model.SearchSubCategoryResponse;

import java.util.List;

public interface LookupService {
    List<SearchCategoryResponse> searchCategory(String categoryName);
    List<SearchSubCategoryResponse> searchSubCategory(Long categoryId, String subcategoryName);
}
