package com.teay.finance.entities;

import com.teay.finance.Type;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @Enumerated(EnumType.STRING)
    private Type type;

    private BigDecimal amount;
    private String description;
    private LocalDateTime today;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name="category_id", nullable = false)
    private Category category;

    public Transaction(){
    }
    public Transaction(Type type, BigDecimal amount, String description, LocalDateTime time, User user, Category category){
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.user = user;
        this.type = type;
        this.today = time.now();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getToday() {
        return today;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
