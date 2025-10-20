package com.snapp.expense_tracker.lookup.domain;

import jakarta.persistence.*;

@Entity
@Table
public class ExpenseSubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    private ExpenseSubCategory category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public ExpenseSubCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseSubCategory category) {
        this.category = category;
    }
}
