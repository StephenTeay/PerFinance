package com.teay.finance.dtos;

import jakarta.validation.constraints.NotNull;

public class CategoryRequest {
    @NotNull(message="A Category must be named")
    private String categoryName;

    @NotNull(message="A user must create a category")
    private Long userId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
