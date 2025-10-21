package com.snapp.expense_tracker.cost.model;


import jakarta.validation.constraints.NotBlank;

public record AddExpenseRequest(Long categoryId,
                                @NotBlank String categoryName,
                                Long subCategoryId,
                                @NotBlank String subCategoryName,
                                @NotBlank double amount) {
}
