package com.snapp.expense_tracker.lookup.domain;

import com.snapp.expense_tracker.lookup.LookupService;
import com.snapp.expense_tracker.lookup.model.SearchCategoryRequest;
import com.snapp.expense_tracker.lookup.model.SearchCategoryResponse;
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
    public List<SearchCategoryResponse> searchCategory(SearchCategoryRequest request) {
        if (request.text() == null || request.text().isEmpty()) {
            return expenseCategoryRepository.findAll().
                    stream().
                    map(expenseCategory -> new SearchCategoryResponse(expenseCategory.getId(), expenseCategory.getCategory())).
                    toList();
        } else {

        }
        return null;
    }
}
