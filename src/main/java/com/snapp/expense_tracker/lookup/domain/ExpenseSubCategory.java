package com.snapp.expense_tracker.lookup.domain;

import jakarta.persistence.*;

@Entity
@Table
public class ExpenseSubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subCategory;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ExpenseCategory category;

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

    public ExpenseCategory getCategory() {
        return category;
    }

    public void setCategory(ExpenseCategory category) {
        this.category = category;
    }
}
