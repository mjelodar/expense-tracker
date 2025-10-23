package com.snapp.expense_tracker.lookup.service;

import com.snapp.expense_tracker.lookup.domain.ExpenseCategory;
import com.snapp.expense_tracker.lookup.domain.ExpenseSubCategory;
import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
import com.snapp.expense_tracker.lookup.model.SearchSubCategoryResponse;
import com.snapp.expense_tracker.lookup.repository.ExpenseCategoryRepository;
import com.snapp.expense_tracker.lookup.repository.ExpenseSubCategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LookupServiceImpl implements LookupService {
    private final ExpenseCategoryRepository expenseCategoryRepository;
    private final ExpenseSubCategoryRepository expenseSubCategoryRepository;

    public LookupServiceImpl(ExpenseCategoryRepository expenseCategoryRepository, ExpenseSubCategoryRepository expenseSubCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
        this.expenseSubCategoryRepository = expenseSubCategoryRepository;
    }

    @Override
    public List<SearchCategoryResponse> searchCategory(String categoryName) {
        List<ExpenseCategory> expenseCategories;
        if (categoryName == null || categoryName.isEmpty()) {
            expenseCategories = expenseCategoryRepository.findAll();
        } else {
            expenseCategories = expenseCategoryRepository.findByCategoryContainingIgnoreCase(categoryName);
        }
        return expenseCategories.
                stream().
                map(expenseCategory -> new SearchCategoryResponse(expenseCategory.getId(), expenseCategory.getCategory())).
                toList();
    }

    @Override
    public List<SearchSubCategoryResponse> searchSubCategory(Long categoryId, String subcategoryName) {
        List<ExpenseSubCategory> expenseSubCategories;
        if (subcategoryName == null || subcategoryName.isEmpty()) {
            expenseSubCategories = expenseSubCategoryRepository.findByCategoryId(categoryId);
        }else {
            expenseSubCategories = expenseSubCategoryRepository.findByCategoryIdAndSubCategoryContainingIgnoreCase(categoryId, subcategoryName);
        }
        return expenseSubCategories.
                stream().
                map(expenseSubCategory -> new SearchSubCategoryResponse(expenseSubCategory.getId(),
                        expenseSubCategory.getSubCategory(),
                        expenseSubCategory.getCategory().getId())).
                toList();
    }
}
