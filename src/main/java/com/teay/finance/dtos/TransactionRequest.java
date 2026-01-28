package com.teay.finance.dtos;

import com.teay.finance.Type;
import com.teay.finance.entities.Category;
import com.teay.finance.entities.User;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRequest {

    @NotNull(message = "A user must exist for a transaction to be made")
    private Long userId;

    @NotNull(message="A transaction must belong to a category")
    private Long categoryId;

    private Type type;
    private BigDecimal amount;
    private String description;
    private LocalDateTime today;

    public LocalDateTime getToday() {
        return LocalDateTime.now();
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
